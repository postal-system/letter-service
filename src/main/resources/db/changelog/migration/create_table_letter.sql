create table if not exists letter (
  id uuid primary key,
  post_office_id serial not null,
  sender varchar(250) not null,
  receiver varchar(250) not null,
  time_stamp timestamp,
  raw_letter jsonb not null,
  content varchar(250)
);