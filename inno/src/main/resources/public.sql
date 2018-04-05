
create database public;
use public;
CREATE TABLE "public"."ay_test" (
    "id" varchar(32) COLLATE "default" NOT NULL,
    "name" varchar(10) COLLATE "default",
    "birth_date" timestamp(6),
    "remark" text COLLATE "default",
    CONSTRAINT "ay_test_pkey" PRIMARY KEY ("id")
);


select * from "public"."ay_test";
