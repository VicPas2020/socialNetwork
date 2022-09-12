INSERT INTO public.usr (id, first_name, last_name, patronymic, sex, birth_date, city, photo_url, about, nick_name,
                        skills, mail, phone, is_deleted)
VALUES ('042119d0-fdd3-4f1b-ab35-7e33568a3494', 'test2', null, null, null, null, null, null, null, null, null, null,
        null, false);
INSERT INTO public.usr (id, first_name, last_name, patronymic, sex, birth_date, city, photo_url, about, nick_name,
                        skills, mail, phone, is_deleted)
VALUES ('a7b520b7-0195-4f32-ad2e-3eb40a5a645c', null, null, null, null, null, null, null, null, null, null, null,
        null, false);
INSERT INTO public.usr (id, first_name, last_name, patronymic, sex, birth_date, city, photo_url, about, nick_name,
                        skills, mail, phone, is_deleted)
VALUES ('bf0d7b78-9c84-4f16-8bab-b0f73c3e8e99', 'firstName1111', 'lastName', null, null, null, null, null, null,
        null, null, null, null, false);


INSERT INTO public.subscriptions (parent, child)
VALUES ('042119d0-fdd3-4f1b-ab35-7e33568a3494', 'bf0d7b78-9c84-4f16-8bab-b0f73c3e8e99');
