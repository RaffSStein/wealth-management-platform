-- Insert Mifid Questionnaire
INSERT INTO mifid_questionnaire (name, valid_from, valid_to, description, questionnaire_version, status)
VALUES ('Mifid 2025', '2025-01-01', '2025-12-31', 'Standard Mifid questionnaire for 2025', 'v1.0', 'ACTIVE');

-- Insert Sections
INSERT INTO mifid_section (title, order_index, questionnaire_id)
VALUES ('Personal Information', 1, (SELECT id FROM mifid_questionnaire WHERE name = 'Mifid 2025'));
INSERT INTO mifid_section (title, order_index, questionnaire_id)
VALUES ('Investment Experience', 2, (SELECT id FROM mifid_questionnaire WHERE name = 'Mifid 2025'));

-- Insert Questions
INSERT INTO mifid_question (text, question_type, is_required, order_index, section_id)
VALUES ('What is your annual income?', 'NUMERIC', true, 1, (SELECT id FROM mifid_section WHERE title = 'Personal Information'));
INSERT INTO mifid_question (text, question_type, is_required, order_index, section_id)
VALUES ('Have you invested in stocks before?', 'SINGLE_CHOICE', true, 2, (SELECT id FROM mifid_section WHERE title = 'Investment Experience'));

-- Insert Answer Options
INSERT INTO mifid_answer_option (option_text, score, order_index, question_id)
VALUES ('Yes', 10, 1, (SELECT id FROM mifid_question WHERE text = 'Have you invested in stocks before?'));
INSERT INTO mifid_answer_option (option_text, score, order_index, question_id)
VALUES ('No', 0, 2, (SELECT id FROM mifid_question WHERE text = 'Have you invested in stocks before?'));

-- Insert Risk Profile Configurations
INSERT INTO risk_profile_configuration (profile_code, min_score, max_score, profile_label, questionnaire_id)
VALUES ('CONSERVATIVE', 0, 10, 'Conservative Profile', (SELECT id FROM mifid_questionnaire WHERE name = 'Mifid 2025'));
INSERT INTO risk_profile_configuration (profile_code, min_score, max_score, profile_label, questionnaire_id)
VALUES ('AGGRESSIVE', 11, 20, 'Aggressive Profile', (SELECT id FROM mifid_questionnaire WHERE name = 'Mifid 2025'));
