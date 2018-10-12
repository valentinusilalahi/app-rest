insert into oauth_client_details (
    client_id, resource_ids, client_secret, 
    scope, authorized_grant_types, web_server_redirect_uri)
values (
    'client001', 'belajar', '$2a$10$rk7SKH0KPDnMWVmFPenbt.dWDgkFd5R03GKpCKzwfRr3fNTlNt03G', 
    'view,edit,approve', 'authorization_code,refresh_token', 'http://localhost:10001/handle-oauth-callback');

insert into oauth_client_details (
    client_id, resource_ids, client_secret, 
    scope, authorized_grant_types, web_server_redirect_uri)
values (
    'client002', 'belajar', '$2a$10$rk7SKH0KPDnMWVmFPenbt.dWDgkFd5R03GKpCKzwfRr3fNTlNt03G', 
    'read,write,admin', 'implicit', 'http://localhost:8080/');

-- grant_types : authorization_code,refresh_token,password,client
