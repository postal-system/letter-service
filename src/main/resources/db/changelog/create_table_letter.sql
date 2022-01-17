create table if not exists letter (
  id uuid primary key,
  raw_letter jsonb not null,
  sender varchar(250) not null,
  content varchar(250),
  receiver varchar(250) not null,
  post_office_id serial not null,
  time_stamp timestamp
);