-- Example inserts for SectionEntity (sections table)
INSERT INTO sections (section_code, section_name, application) VALUES
  ('HOME', 'HOME', 'WMP_B2C'),
  ('ORDERS', 'ORDERS', 'WMP_B2C'),
  ('PORTFOLIO', 'PORTFOLIO', 'WMP_B2C'),
  ('SEARCH', 'SEARCH', 'WMP_B2C');

-- Example inserts for PermissionEntity (permissions table)
INSERT INTO permissions (permission_code, permission_name) VALUES
  ('VIEW', 'VIEW_PERMISSION'),
  ('USE', 'USE_PERMISSION'),
  ('EDIT', 'EDIT_PERMISSION'),
  ('DOWNLOAD', 'DOWNLOAD_PERMISSION');

-- Example inserts for FeatureEntity (features table)
-- home section features
INSERT INTO features (feature_code, feature_name, section_id) VALUES
  ('view_orders', 'VIEW_ORDERS', (SELECT id FROM sections WHERE section_name = 'HOME' AND application = 'WMP_B2C')),
  ('view_notifications', 'VIEW_NOTIFICATIONS', (SELECT id FROM sections WHERE section_name = 'HOME' AND application = 'WMP_B2C')),
  ('view_portfolio_resume', 'VIEW_PORTFOLIO_RESUME', (SELECT id FROM sections WHERE section_name = 'HOME' AND application = 'WMP_B2C'));
-- orders section features
INSERT INTO features (feature_code, feature_name, section_id) VALUES
  ('view_orders', 'VIEW_ORDERS', (SELECT id FROM sections WHERE section_name = 'ORDERS' AND application = 'WMP_B2C')),
  ('view_order_detail', 'VIEW_ORDER_DETAIL', (SELECT id FROM sections WHERE section_name = 'ORDERS' AND application = 'WMP_B2C')),
  ('revoke_order', 'REVOKE_ORDER', (SELECT id FROM sections WHERE section_name = 'ORDERS' AND application = 'WMP_B2C')),
  ('place_order', 'PLACE_ORDER', (SELECT id FROM sections WHERE section_name = 'ORDERS' AND application = 'WMP_B2C')),
  ('change_order', 'CHANGE_ORDER', (SELECT id FROM sections WHERE section_name = 'ORDERS' AND application = 'WMP_B2C'));
-- portfolio section features
INSERT INTO features (feature_code, feature_name, section_id) VALUES
  ('view_portfolio_details', 'VIEW_PORTFOLIO_DETAILS', (SELECT id FROM sections WHERE section_name = 'PORTFOLIO' AND application = 'WMP_B2C')),
  ('rebalance_portfolio', 'REBALANCE_PORTFOLIO', (SELECT id FROM sections WHERE section_name = 'PORTFOLIO' AND application = 'WMP_B2C'));
-- search section features
INSERT INTO features (feature_code, feature_name, section_id) VALUES
  ('search_products', 'SEARCH_PRODUCTS', (SELECT id FROM sections WHERE section_name = 'SEARCH' AND application = 'WMP_B2C'));
