CREATE TABLE film(
                     film_id SERIAL PRIMARY KEY NOT NULL,
                     title VARCHAR(255),
                     author VARCHAR(255)
);

CREATE TABLE account(
                        user_id BIGSERIAL PRIMARY KEY NOT NULL,
                        username VARCHAR(100),
                        password VARCHAR(255)
);

CREATE TABLE review(
                       review_id BIGSERIAL PRIMARY KEY NOT NULL,
                       user_id BIGINT,
                       film_id INT,
                       text VARCHAR(1000),

                       CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES account(user_id),
                       CONSTRAINT film_id_fk FOREIGN KEY (film_id) REFERENCES film(film_id)

);

CREATE TABLE favourite_film (
                                favourite_id BIGSERIAL PRIMARY KEY NOT NULL,
                                user_id BIGINT NOT NULL,
                                film_id INT NOT NULL,

                                CONSTRAINT favourite_user_fk FOREIGN KEY (user_id) REFERENCES account(user_id) ON DELETE CASCADE,
                                CONSTRAINT favourite_film_fk FOREIGN KEY (film_id) REFERENCES film(film_id) ON DELETE CASCADE,
                                CONSTRAINT unique_favourite UNIQUE (user_id, film_id)
);
