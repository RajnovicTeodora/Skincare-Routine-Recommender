INSERT INTO role_table (id, name)
VALUES (1, 'ADMIN');
INSERT INTO role_table (id, name)
VALUES (2, 'DERMATOLOGIST');
INSERT INTO role_table (id, name)
VALUES (3, 'PATIENT');

-- 123 password
INSERT INTO system_user (username, password, name, surname, email, role_id)
VALUES ('user', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Marko', 'Markovic', 'user@example.com',
        3);
INSERT INTO system_user (username, password, name, surname, email, role_id)
VALUES ('admin', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Nikola', 'Nikolic',
        'admin@example.com', 1);
INSERT INTO system_user (username, password, name, surname, email, role_id)
VALUES ('lazar', '$2a$04$Vbug2lwwJGrvUXTj6z7ff.97IzVBkrJ1XfApfGNl.Z695zqcnPYra', 'Lazar', 'Lazic', 'laza@example.com',
        2);

INSERT INTO admin (id)
VALUES (2);
INSERT INTO patient (id, birthday, gender)
VALUES (1, '2020-10-31', 'MALE');
INSERT INTO dermatologist (id)
VALUES (3);

-- Skin Type Characteristics
INSERT INTO skin_type_characteristics (skin_type)
VALUES ('COMBINED');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (1, 'OILY_SKIN');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (1, 'VISIBLE_PORES');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (1, 'DRY_SKIN');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (1, 'BLEMISHES');

INSERT INTO skin_type_characteristics (skin_type)
VALUES ('OILY');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (2, 'BLEMISHES');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (2, 'VISIBLE_PORES');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (2, 'OILY_SKIN');

INSERT INTO skin_type_characteristics (skin_type)
VALUES ('NORMAL');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (3, 'RADIANT_COMPLEXION');

INSERT INTO skin_type_characteristics (skin_type)
VALUES ('DRY');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (4, 'TIGHT_SKIN');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (4, 'DRY_SKIN');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (4, 'ITCHY_SKIN');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (4, 'RED_PATCHES');

INSERT INTO skin_type_characteristics (skin_type)
VALUES ('SENSITIVE');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (5, 'DRY_SKIN');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (5, 'BLEMISHES');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (5, 'ITCHY_SKIN');
INSERT INTO skin_characteristics (skin_type_id, characteristic)
VALUES (5, 'RED_PATCHES');

-- Ingredients

-- AHAs
INSERT INTO ingredient (name, benefiting_acne, benefiting_age, benefiting_skin_type)
VALUES ('Glycolic acid', 'BLACKHEADS', 'ADULT', 'SENSITIVE'); --1
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (1, 'REDUCE_WRINKLES');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (1, 'IMPROVE_PIGMENTATION');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (1, 'REDUCE_BREAKOUTS');

INSERT INTO ingredient (name, benefiting_acne, benefiting_age, benefiting_skin_type)
VALUES ('Lactic acid', 'PAPULES', 'ADULT', 'DRY'); --2 DRY?
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (2, 'REDUCE_BREAKOUTS');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (2, 'REDUCE_OILINESS');

INSERT INTO ingredient (name, benefiting_age, benefiting_skin_type)
VALUES ('Tartaric acid', 'ADULT', 'DRY'); --3 all acne benefiting
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (3, 'HYDRATE_SKIN');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (3, 'REDUCE_WRINKLES');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (3, 'REDUCE_ACNE_SCARING');

INSERT INTO ingredient (name, benefiting_acne, benefiting_age, benefiting_skin_type)
VALUES ('Malic  acid', 'BLACKHEADS', 'ADULT', 'SENSITIVE'); --4
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (4, 'REDUCE_BREAKOUTS');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (4, 'REDUCE_OILINESS');

-- Beta-hydroxy acid - BHAs
INSERT INTO ingredient (name, benefiting_acne, benefiting_age, benefiting_skin_type)
VALUES ('Citric acid', 'WHITEHEADS', 'ADULT', 'OILY'); --5
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (5, 'REDUCE_ACNE');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (5, 'REDUCE_OILINESS');

INSERT INTO ingredient (name, benefiting_acne, benefiting_age, benefiting_skin_type)
VALUES ('Salicylic acid', 'WHITEHEADS', 'YOUTH', 'OILY '); --6
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (6, 'REDUCE_WRINKLES');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (6, 'REDUCE_ACNE');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (6, 'REDUCE_OILINESS');

-- Not for sensitive, dry, oily skin
INSERT INTO ingredient (name, benefiting_age, benefiting_skin_type)
VALUES ('Hydroquinone', 'ADULT', 'COMBINED'); --7
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (7, 'REDUCE_WRINKLES');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (7, 'REDUCE_SUN_DAMAGE');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (7, 'REDUCE_ACNE_SCARING');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (7, 'IMPROVE_PIGMENTATION');

-- Retinoids
INSERT INTO ingredient (name, benefiting_acne, benefiting_age, benefiting_skin_type)
VALUES ('Retinol', 'BLACKHEADS', 'ADULT', 'OILY'); --8
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (8, 'REDUCE_WRINKLES');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (8, 'REDUCE_SUN_DAMAGE');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (8, 'IMPROVE_PIGMENTATION');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (8, 'REDUCE_ACNE');

-- VITAMIN C
INSERT INTO ingredient (name, benefiting_age, benefiting_skin_type)
VALUES ('L-ascorbic acid', 'YOUTH', 'NORMAL'); --9
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (9, 'REDUCE_ACNE_SCARING');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (9, 'REDUCE_SUN_DAMAGE');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (9, 'IMPROVE_PIGMENTATION');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (9, 'HYDRATE_SKIN');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (9, 'REDUCE_REDNESS');

INSERT INTO ingredient (name, benefiting_acne, benefiting_age, benefiting_skin_type)
VALUES ('Sodium ascorbyl phosphate', 'CYSTS', 'YOUTH', 'OILY'); --10
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (10, 'REDUCE_ACNE_SCARING');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (10, 'REDUCE_SUN_DAMAGE');

--
INSERT INTO ingredient (name, benefiting_acne, benefiting_age, benefiting_skin_type)
VALUES ('Hyaluronic acid', 'CYSTS', 'YOUTH', 'DRY'); --11
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (11, 'HYDRATE_SKIN');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (11, 'REDUCE_BREAKOUTS');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (11, 'IMPROVE_PIGMENTATION');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (11, 'REDUCE_REDNESS');

INSERT INTO ingredient (name, benefiting_age, benefiting_skin_type)
VALUES ('Copper peptide', 'YOUTH', 'OILY'); --12
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (12, 'REDUCE_ACNE');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (12, 'IMPROVE_PIGMENTATION');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (12, 'REDUCE_REDNESS');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (12, 'REDUCE_ACNE_SCARING');

-- And papules
INSERT INTO ingredient (name, benefiting_acne, benefiting_age, benefiting_skin_type)
VALUES ('Niacinamide', 'PUSTULES', 'YOUTH', 'SENSITIVE'); -- 13
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (13, 'REDUCE_ACNE');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (13, 'REDUCE_ACNE_SCARING');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (13, 'IMPROVE_PIGMENTATION');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (13, 'IMPROVE_SKIN_ELASTICITY');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (13, 'REDUCE_REDNESS');

-- For hormonal acne: cysts, whiteheads, blackheads
INSERT INTO ingredient (name, benefiting_acne, benefiting_age, benefiting_skin_type)
VALUES ('Coenzyme Q10', 'CYSTS', 'ADULT', 'SENSITIVE'); -- 14
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (14, 'IMPROVE_SKIN_ELASTICITY');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (14, 'REDUCE_SUN_DAMAGE');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (14, 'REDUCE_WRINKLES');

-- Dry/combination skin
INSERT INTO ingredient (name, benefiting_acne, benefiting_age, benefiting_skin_type)
VALUES ('Sulfur', 'NODULES', 'ADULT', 'COMBINED'); -- 15
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (15, 'REDUCE_ACNE');
INSERT INTO benefiting_goals (ingredient_id, goal)
VALUES (15, 'REDUCE_OILINESS');

-- Products
INSERT INTO product (name, product_type, manufacturer, usage_instructions, image, price)
VALUES ('CeraVe Moisturizing Cream', 'CLEANSER', 'CeraVe',
        'Use morning and evening. Apply to wet hands and massage into the skin, then rinse thoroughly.', 'iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO
        9TXL0Y4OHwAAAABJRU5ErkJggg==', 32.0);
INSERT INTO product_ingredient (product_id, ingredient_id)
VALUES (1, 11); -- Hyaluronic acid

INSERT INTO product (name, product_type, manufacturer, usage_instructions, image, price)
VALUES ('CeraVe Moisturizing Lotion', 'MOISTURIZER', 'CeraVe', ' Apply liberally as often as needed.', 'iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO
        9TXL0Y4OHwAAAABJRU5ErkJggg==', 32.0);
INSERT INTO product_ingredient (product_id, ingredient_id)
VALUES (2, 11); -- Hyaluronic acid
INSERT INTO product_ingredient (product_id, ingredient_id)
VALUES (2, 13); -- Niacinamide

INSERT INTO product (name, product_type, manufacturer, usage_instructions, image, price)
VALUES ('CeraVe Daily Moisturizing Lotion', 'MOISTURIZER', 'CeraVe', ' Apply liberally as often as needed.', 'iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO
        9TXL0Y4OHwAAAABJRU5ErkJggg==',
        32.0);
INSERT INTO product_ingredient (product_id, ingredient_id)
VALUES (3, 11); -- Hyaluronic acid

INSERT INTO product (name, product_type, manufacturer, usage_instructions, image, price)
VALUES ('CeraVe Hydrating Cleanser', 'CLEANSER', 'CeraVe',
        'Use morning and evening. Apply to wet hands and massage into the skin, then rinse thoroughly.', 'iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO
        9TXL0Y4OHwAAAABJRU5ErkJggg==', 32.0);
INSERT INTO product_ingredient (product_id, ingredient_id)
VALUES (4, 5); -- Citric acid
INSERT INTO product_ingredient (product_id, ingredient_id)
VALUES (4, 11); -- Hyaluronic acid
INSERT INTO product_ingredient (product_id, ingredient_id)
VALUES (4, 13);
-- Niacinamide

-- Routines
INSERT INTO routine (start_date, patient_id)
VALUES ('2021-10-10', 1);
INSERT INTO routine_product (routine_id, product_id)
VALUES (1, 1);
INSERT INTO routine_product (routine_id, product_id)
VALUES (1, 2);

INSERT INTO routine (start_date, patient_id)
VALUES ('2021-10-11', 1);
INSERT INTO routine_product (routine_id, product_id)
VALUES (2, 3);
INSERT INTO routine_product (routine_id, product_id)
VALUES (2, 4);

-- Product Reactions
INSERT INTO product_reaction (reaction, patient_id, product_id)
VALUES ('Bad reaction', 1, 4);