INSERT INTO s_permission (id, permission_value, permission_label) VALUES
  ('transaction_view', 'VIEW_TRANSAKSI', 'Melihat data transaksi'),
  ('transaction_edit', 'EDIT_TRANSAKSI', 'Entri Transaksi'),
  ('transaction_approve', 'APPROVE_TRANSAKSI', 'Approve Transaksi');
  
INSERT INTO s_role (id, name, description) VALUES
  ('supervisor', 'SUPERVISOR', 'Supervisor'),
  ('staff', 'STAFF', 'Staff'),
  ('manager', 'MANAGER', 'Manager');

INSERT INTO s_role_permission (id_role, id_permission) VALUES
  ('staff', 'transaction_view'),
  ('manager', 'transaction_view'),
  ('supervisor', 'transaction_view'),
  ('manager', 'transaction_edit'),
  ('supervisor', 'transaction_approve');

INSERT INTO s_user (id, active, username, id_role) VALUES
  ('u001', true, 'user001', 'staff');

INSERT INTO s_user (id, active, username, id_role) VALUES
  ('u002', true, 'user002', 'manager');

INSERT INTO s_user (id, active, username, id_role) VALUES
  ('u003', true, 'user003', 'supervisor');

INSERT INTO s_user_password (id_user, password) VALUES
  -- password : test123
  ('u001', '$2a$13$d2GZHGr6gedUiNk8r3Pbo.Jc8eH7oBVdTta.WGMG9g1dO9T4hlNPG');

INSERT INTO s_user_password (id_user, password) VALUES
  -- password : test123
  ('u002', '$2a$13$d2GZHGr6gedUiNk8r3Pbo.Jc8eH7oBVdTta.WGMG9g1dO9T4hlNPG');

INSERT INTO s_user_password (id_user, password) VALUES
  -- password : test123
  ('u003', '$2a$13$d2GZHGr6gedUiNk8r3Pbo.Jc8eH7oBVdTta.WGMG9g1dO9T4hlNPG');