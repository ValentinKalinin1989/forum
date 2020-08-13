CREATE TABLE IF NOT EXISTS authorities
(
    id        SERIAL PRIMARY KEY,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE IF NOT EXISTS users
(
    id           SERIAL PRIMARY KEY,
    username     VARCHAR(50)  NOT NULL unique,
    password     VARCHAR(100) NOT NULL,
    enabled      BOOLEAN DEFAULT TRUE,
    authority_id INT          NOT NULL REFERENCES authorities (id)
);

CREATE TABLE IF NOT EXISTS post
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(100)  NOT NULL,
    description VARCHAR(1000) NOT NULL,
    created     DATE,
    user_id     INT           NOT NULL REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS message
(
    id      SERIAL PRIMARY KEY,
    text    VARCHAR(1000) NOT NULL,
    created DATE,
    user_id INT           NOT NULL REFERENCES users (id),
    post_id INT           NOT NULL REFERENCES post (id)
)