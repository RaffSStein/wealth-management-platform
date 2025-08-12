@startuml
actor FE as "Frontend (WMP)"
participant "customer-service" as Customer
queue "create-document-topic" as createDocumentTopic
participant "document-service" as Document
queue "document-validated-topic" as documentValidationTopic
participant "aml-external-systems" as ExternalSystems


== Step 1 - customer personal information ==
activate FE
note right of FE: Customer starts onboarding\nprocess for a new customer
note right of FE: Customer enters personal\ninformation and documents into the form
FE -> Customer: POST /customers/init\n(customer data)
deactivate FE
activate Customer
Customer -> Customer: Customer basic\ninfo validation
alt Customer data valid
    Customer -> Customer: Save customer info to DB
    Customer -> Customer: Generate customer ID
    Customer -> createDocumentTopic: publish create document request\n(customer ID, documents)
    Customer -> FE: 200 OK\n(customer ID)
    deactivate Customer
    createDocumentTopic --> Document: consume document\ncreation request
    activate Document
    Document -> Document: Validate documents
    alt Documents valid format
        Document -> Document: Save documents to DB
        Document -> documentValidationTopic: publish document\ncreation success
    else Documents invalid format
        Document -> documentValidationTopic: publish document\ncreation failure
    end
    deactivate Document
documentValidationTopic --> Customer: consume document validation result
activate Customer
Customer -> Customer: store document validation result
else Customer data invalid
    Customer -> FE: 400 Bad Request\n(validation errors)
    note right of FE: Customer needs to correct\ninvalid data
end
deactivate Customer

== Step 2 - customer financial information ==
activate FE
note right of FE: Retrieve financial types\nto display in form
FE -> Customer: GET /customers/financials/types\n
deactivate FE

activate Customer
Customer -> FE: 200 OK\n(financial types)
deactivate Customer
activate FE
note right of FE: Customer selects financial types\nand enters financial data
FE -> Customer: POST /customers/{customerId}/financials\n(financial data)
deactivate FE
activate Customer
Customer -> Customer: Customer financial\ninfo & step validation
alt Financial data valid
    Customer -> Customer: Save financial info to DB
    Customer -> FE: 200 OK\n
    activate FE
else Financial data invalid
    Customer -> FE: 400 Bad Request\n(validation errors)
    note right of FE: Customer needs to correct\ninvalid financial data
end
deactivate FE
deactivate Customer

== Step 3 - customer financial goals ==
activate FE
note right of FE: Retrieve financial goal types\nto display in form
FE -> Customer: GET /customers/financials/goal/types\n
deactivate FE
activate Customer
Customer -> FE: 200 OK\n(goal types)
deactivate Customer
activate FE
note right of FE: Customer selects financial goals\nand enters financial data
FE -> Customer: POST /customers/{customerId}/goals\n(goals data)
deactivate FE
activate Customer
Customer -> Customer: Customer goals\ninfo & step validation
alt Goals data valid
Customer -> Customer: Save Goals info to DB
Customer -> FE: 200 OK\n
else Goals data invalid
Customer -> FE: 400 Bad Request\n(validation errors)
deactivate Customer
note right of FE: Customer needs to correct\ninvalid goals data
end


== Step 4 - MiFID and Risk Profiling ==
activate FE
note right of FE: Retrieve MiFID and risk\nprofiling questions
FE -> Customer: GET /customers/{customerId}/mifid/questions
deactivate FE
activate Customer
Customer -> FE: 200 OK\n(MiFID questions by section)
deactivate Customer
activate FE
note right of FE: Customer answers all MiFID questions
FE -> Customer: POST /customers/{customerId}/mifid/answers\n(MiFID answers)
deactivate FE
activate Customer
Customer -> Customer: Validate MiFID answers
Customer -> Customer: Save MiFID answers to DB
Customer -> Customer: Calculate customer risk profile
Customer -> FE: 200 OK\n(customer profiling data results)
deactivate Customer
activate FE
FE -> FE: Display customer risk profile
FE -> FE: Display end page\nwith next steps\n(wait for onboarding approval)
FE -> FE: return to Home page
deactivate FE



== Step 5 - AML verification ==
activate Customer
Customer -> ExternalSystems: Start AML verification
deactivate Customer
activate ExternalSystems
ExternalSystems -> ExternalSystems: Validate AML data
ExternalSystems -> ExternalSystems: Check against\nAML/PEP/sanctions lists...
ExternalSystems -> Customer: 200 OK\n(AML verification result)
deactivate ExternalSystems
activate Customer
Customer -> Customer: Store AML verification result
deactivate Customer
@enduml