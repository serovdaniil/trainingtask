create table EMPLOYEE
(
    ID_EMPLOYEE INTEGER identity
        constraint EMPLOYEE_PK
            primary key,
    FIRTS_NAME  CHARACTER(40),
    LAST_NAME   CHARACTER(40) not null,
    PATRONYMIC  CHARACTER(40) not null,
    POSITION    CHARACTER(40)
);

create table PROJECT
(
    ID_PROJECT   INTEGER identity
        constraint PROJECT_PK
            primary key,
    NAME_PROJECT INTEGER not null,
    DESCRIPTION  INTEGER not null
);

create table STATUS_TASK
(
    ID_STATUS   INTEGER identity
        constraint STATUS_TASK_PK
            primary key,
    NAME_STATUS CHARACTER(40) not null
);

create table TASK
(
    ID_TASK      INTEGER identity
        constraint TASK_PK
            primary key,
    STATUS_TASK  INTEGER       not null
        constraint STATUS_TASK
            references STATUS_TASK,
    NAME_TASK    CHARACTER(40) not null,
    NAME_PROJECT INTEGER       not null
        constraint NAME_PROJECT
            references PROJECT,
    NAME_JOB     CHARACTER(40) not null,
    START_DATE   DATE          not null,
    FINISH_DATE  DATE,
    EMPLOYEE_ID  INTEGER       not null
        constraint "Employee"
            references EMPLOYEE
);

