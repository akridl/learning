
    create sequence Contact_SEQ start with 1 increment by 50;

    create sequence Person_SEQ start with 1 increment by 50;

    create table Contact (
        id bigint not null,
        type varchar(255) check (type in ('EMAIL','PHONE')),
        value varchar(255) unique,
        owner_id bigint,
        primary key (id)
    );

    create table Person (
        id bigint not null,
        birthday date,
        familyName varchar(255),
        givenName varchar(255),
        primary key (id)
    );

    alter table if exists Contact 
       add constraint fk_person_contact 
       foreign key (owner_id) 
       references Person;
