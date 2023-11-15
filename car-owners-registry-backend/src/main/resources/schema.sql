CREATE TABLE IF NOT EXISTS users (
    id int4 NOT NULL,
    name varchar(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS cars (
    id int4 NOT NULL,
    make varchar(255) NOT NULL,
    model varchar(255) NOT NULL,
    numberplate varchar(6) NOT NULL,
    user_id int4 NULL,
    CONSTRAINT cars_pkey PRIMARY KEY (id),
    CONSTRAINT user_id_constraint FOREIGN KEY (user_id) REFERENCES public.users(id)
);

CREATE INDEX IF NOT EXISTS index_users_name ON users USING btree (name);
CREATE INDEX IF NOT EXISTS index_users_name_desc ON users USING btree (name DESC);

--CREATE INDEX IF NOT EXISTS index_cars_make ON cars USING btree (make);
CREATE INDEX IF NOT EXISTS index_cars_make_desc ON cars USING btree (make DESC);
CREATE INDEX IF NOT EXISTS index_cars_model ON cars USING btree (model);
CREATE INDEX IF NOT EXISTS index_cars_model_desc ON cars USING btree (model DESC);
CREATE INDEX IF NOT EXISTS index_cars_numberplate ON cars USING btree (numberplate);
CREATE INDEX IF NOT EXISTS index_cars_numberplate_desc ON cars USING btree (numberplate DESC);