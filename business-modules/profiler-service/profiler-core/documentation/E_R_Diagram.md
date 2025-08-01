@startuml

' =====================
' Entity: SectionEntity
' =====================
class SectionEntity {
    UUID id
    String application
    String sectionCode
    String sectionName
    List<FeatureEntity> features
}

' =====================
' Entity: FeatureEntity
' =====================
class FeatureEntity {
    UUID id
    String featureCode
    String featureName
    SectionEntity section
}

' =====================
' Entity: PermissionEntity
' =====================
class PermissionEntity {
    UUID id
    String permissionCode
    String permissionName
}

' =====================
' Entity: UserFeaturePermissionEntity
' =====================
class UserFeaturePermissionEntity {
    UUID id
    String userEmail
    String bankCode
    FeatureEntity feature
    Set<PermissionEntity> permissions
}

' =====================
' Relationships
' =====================
SectionEntity "1" --o "*" FeatureEntity : features
FeatureEntity "*" --o "1" SectionEntity : section
UserFeaturePermissionEntity "*" --o "1" FeatureEntity : feature
UserFeaturePermissionEntity "*" --o "*" PermissionEntity : permissions

@enduml

