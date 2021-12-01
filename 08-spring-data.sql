create database spring;

create table spring.public.user_table(id serial primary key, "name" text, email text);
create table spring.public.event_table(id serial primary key, "title" text, "date" date);
create table spring.public.ticket_table(id serial primary key, event_id bigint, user_id bigint, category text, place bigint);
create table spring.public.user_account_table(id serial primary key, user_id bigint, "money" bigint);

ALTER TABLE spring.public.ticket_table
    ADD CONSTRAINT fk_ticket_table_user_id FOREIGN KEY (user_id) REFERENCES spring.public.user_table (id);

ALTER TABLE spring.public.ticket_table
    ADD CONSTRAINT fk_ticket_table_event_id FOREIGN KEY (event_id) REFERENCES spring.public.event_table (id);
   
ALTER TABLE spring.public.user_account_table
    ADD CONSTRAINT fk_user_account_table_user_id FOREIGN KEY (user_id) REFERENCES spring.public.user_table (id);
