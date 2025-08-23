--TODO: answer scores (now fixed at 10) and risk profile configurations to be defined
-- Insert Mifid Questionnaire
INSERT INTO mifid_questionnaire (name, valid_from, valid_to, description, questionnaire_version, status)
VALUES ('Mifid 2025', '2025-01-01', '2025-12-31', 'Standard Mifid questionnaire for 2025', '1.0', 'ACTIVE');

-- Insert Sections - 5 sections: Personal Information, Investment Experience, Financial Situation, Risk Tolerance, ESG Preferences
INSERT INTO mifid_section (title, order_index, questionnaire_id)
VALUES ('Personal Information', 1, (SELECT id FROM mifid_questionnaire WHERE name = 'Mifid 2025'));
INSERT INTO mifid_section (title, order_index, questionnaire_id)
VALUES ('Investment Experience', 2, (SELECT id FROM mifid_questionnaire WHERE name = 'Mifid 2025'));
INSERT INTO mifid_section (title, order_index, questionnaire_id)
VALUES ('Financial Situation', 3, (SELECT id FROM mifid_questionnaire WHERE name = 'Mifid 2025'));
INSERT INTO mifid_section (title, order_index, questionnaire_id)
VALUES ('Risk Tolerance', 4, (SELECT id FROM mifid_questionnaire WHERE name = 'Mifid 2025'));
INSERT INTO mifid_section (title, order_index, questionnaire_id)
VALUES ('ESG Preferences', 5, (SELECT id FROM mifid_questionnaire WHERE name = 'Mifid 2025'));

-- questions for Personal Information
INSERT INTO mifid_question (text, question_type, is_required, order_index, code, section_id)
VALUES ('What is your annual income?', 'NUMERIC', true, 1, 'PI-001', (SELECT id FROM mifid_section WHERE title = 'Personal Information'));

-- answer options for Personal Information
INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('Yes', 10, 1, 'PI-001', (SELECT id FROM mifid_question WHERE code = 'PI-001'));


-- questions for Investment Experience
INSERT INTO mifid_question (text, question_type, is_required, order_index, code, section_id)
VALUES ('Can the capital invested in a financial product issued by a bank be reduced or eliminated?', 'SINGLE', true, 1, 'IE-001', (SELECT id FROM mifid_section WHERE title = 'Investment Experience'));

INSERT INTO mifid_question (text, question_type, is_required, order_index, code, section_id)
VALUES ('What is the effect of leverage on an investment?', 'SINGLE', true, 1, 'IE-002', (SELECT id FROM mifid_section WHERE title = 'Investment Experience'));

INSERT INTO mifid_question (text, question_type, is_required, order_index, code, section_id)
VALUES ('What are the derivative instruments?', 'SINGLE', true, 1, 'IE-003', (SELECT id FROM mifid_section WHERE title = 'Investment Experience'));

-- answer options for Investment Experience
-- question IE-001
INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('No, as financial support is always provided for banking intermediaries in crisis.', 10, 1, 'PI-001', (SELECT id FROM mifid_question WHERE code = 'IE-001'));

INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('No because banks can''t fail.', 10, 2, 'PI-002', (SELECT id FROM mifid_question WHERE code = 'IE-001'));

INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('Yes, in the event of a bank failure or risk of failure.', 10, 3, 'PI-003', (SELECT id FROM mifid_question WHERE code = 'IE-001'));

INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('I do not know.', 10, 4, 'PI-004', (SELECT id FROM mifid_question WHERE code = 'IE-001'));

-- question IE-002
INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('It allows you to make a gain when a specific event occurs (such as an increase in the price of a related security/index, change in interest rates, etc.)', 10, 1, 'PI-005', (SELECT id FROM mifid_question WHERE code = 'IE-002'));

INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('The leverage effect allows you to invest in foreign markets that would otherwise be inaccessible.', 10, 2, 'PI-006', (SELECT id FROM mifid_question WHERE code = 'IE-002'));

INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('It amplifies the potential gain/loss and consequently the risk level of the investment.', 10, 3, 'PI-007', (SELECT id FROM mifid_question WHERE code = 'IE-002'));

INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('It is the operating mechanism of repurchase agreements,', 10, 4, 'PI-008', (SELECT id FROM mifid_question WHERE code = 'IE-002'));

-- question IE-003
INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('They are financial instruments whose redemption value is always guaranteed.', 10, 1, 'PI-009', (SELECT id FROM mifid_question WHERE code = 'IE-003'));

INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('They are financial instruments whose value depends on the performance of the underlying investment and may result in losses exceeding the invested capital.', 10, 2, 'PI-010', (SELECT id FROM mifid_question WHERE code = 'IE-003'));

INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('They are insurance policies intended for institutional investors.', 10, 3, 'PI-011', (SELECT id FROM mifid_question WHERE code = 'IE-003'));

INSERT INTO mifid_answer_option (option_text, score, order_index, code, question_id)
VALUES ('They are instruments that allow investment in unlisted shares.', 10, 4, 'PI-012', (SELECT id FROM mifid_question WHERE code = 'IE-003'));

--TODO
-- questions for Financial Situation

-- answer options for Financial Situation

-- questions for Risk Tolerance

-- answer options for Risk Tolerance

-- questions for ESG Preferences

-- answer options for ESG Preferences

-- Insert Risk Profile Configurations
INSERT INTO risk_profile_configuration (profile_code, min_score, max_score, profile_label, questionnaire_id)
VALUES ('CONSERVATIVE', 0, 10, 'Conservative Profile', (SELECT id FROM mifid_questionnaire WHERE name = 'Mifid 2025'));
INSERT INTO risk_profile_configuration (profile_code, min_score, max_score, profile_label, questionnaire_id)
VALUES ('AGGRESSIVE', 11, 20, 'Aggressive Profile', (SELECT id FROM mifid_questionnaire WHERE name = 'Mifid 2025'));
