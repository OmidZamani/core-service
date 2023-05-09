
SET SQLBLANKLINES ON;
SET DEFINE OFF;
ALTER SESSION SET NLS_DATE_FORMAT = 'MM/DD/SYYYY HH24:MI:SS';
ALTER SESSION SET NLS_TIMESTAMP_TZ_FORMAT = 'MM/DD/SYYYY HH24:MI:SS.FF TZH:TZM';
ALTER SESSION SET NLS_TIMESTAMP_FORMAT = 'MM/DD/SYYYY HH24:MI:SS.FF';
ALTER SESSION SET NLS_NUMERIC_CHARACTERS = '.,';
ALTER SESSION SET NLS_NCHAR_CONV_EXCP = FALSE;
ALTER SESSION SET TIME_ZONE = '+04:30';

INSERT INTO HUBUSER.TBL_COUNTRYDEVISION(PK_COUNTRYDEVISION_ID, AUTHOR, CREATEDDATE, MODIFIEDDATE, MODIFIER, CODE, TYPE, NAME, FK_PARENT_ID) VALUES(1, NULL, NULL, NULL, NULL, 'azarbaicanSH', 0, 'آذربایجان شرقی', NULL);
INSERT INTO HUBUSER.TBL_COUNTRYDEVISION(PK_COUNTRYDEVISION_ID, AUTHOR, CREATEDDATE, MODIFIEDDATE, MODIFIER, CODE, TYPE, NAME, FK_PARENT_ID) VALUES(2, NULL, NULL, NULL, NULL, 'tbz', 0, 'تبریز', 1);
COMMIT;

INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(2,'آذربایجان غربی','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(3,'اردبیل','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(4,'اصفهان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(5,'البرز','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(6,'ایلام','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(7,'بوشهر','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(8,'تهران','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(9,'چهارمحال و بختیاری','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(10,'خراسان جنوبی','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(11,'خراسان رضوی','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(12,'خراسان شمالی','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(13,'خوزستان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(14,'زنجان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(15,'سمنان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(16,'سیستان و بلوچستان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(17,'فارس','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(18,'قزوین','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(19,'قم','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(20,'کردستان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(21,'کرمان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(22,'کرمانشاه','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(23,'کهگیلویه و بویراحمد','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(24,'گلستان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(25,'لرستان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(26,'گیلان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(27,'مازندران','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(28,'مرکزی','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(29,'هرمزگان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(30,'همدان','PROVINCE','-1');
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
(31,'یزد','PROVINCE','-1');


INSERT INTO public.lookup(id, content, look_up_type, parent_id)	VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسکو','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اهر','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایلخچی','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آبش احمد','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آذرشهر','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آقکند','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باسمنج','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بخشایش','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بستان آباد','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بناب','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بناب جدید','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تبریز','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ترک','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ترکمانچای','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تسوج','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تیکمه داش','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جلفا','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خاروانا','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خامنه','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خراجو','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خسروشهر','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خضرلو','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خمارلو','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خواجه','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دوزدوزان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زرنق','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زنوز','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سراب','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سردرود','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سهند','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیس','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیه رود','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شبستر','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شربیان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شرفخانه','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شندآباد','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صوفیان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'عجب شیر','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قره آغاج','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کشکسرای','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کلوانق','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کلیبر','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوزه کنان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گوگان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لیلان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مراغه','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مرند','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ملکان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ملک کیان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ممقان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مهربان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میانه','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نظرکهریزی','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هادی شهر','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هرگلان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هریس','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هشترود','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هوراند','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'وایقان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ورزقان','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'یامچی','CITY',1);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ارومیه','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اشنویه','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایواوغلی','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آواجیق','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باروق','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بازرگان','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بوکان','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پلدشت','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پیرانشهر','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تازه شهر','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تکاب','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چهاربرج','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خوی','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دیزج دیز','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ربط','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سردشت','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرو','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سلماس','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیلوانه','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیمینه','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیه چشمه','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شاهین دژ','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شوط','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فیرورق','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قره ضیاءالدین','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قطور','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قوشچی','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کشاورز','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گردکشانه','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ماکو','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمدیار','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمودآباد','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مهاباد','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میاندوآب','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میرآباد','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نالوس','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نقده','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نوشین','CITY',2);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اردبیل','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اصلاندوز','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آبی بیگلو','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بیله سوار','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پارس آباد','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تازه کند','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تازه کندانگوت','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جعفرآباد','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خلخال','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رضی','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرعین','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'عنبران','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فخرآباد','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کلور','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوراییم','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گرمی','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گیوی','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لاهرود','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مشگین شهر','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نمین','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نیر','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هشتجین','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هیر','CITY',3);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ابریشم','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ابوزیدآباد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اردستان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اژیه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اصفهان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'افوس','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'انارک','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایمانشهر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آران','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بادرود','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باغ بهادران','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بافران','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'برزک','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'برف انبار','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بهاران شهر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بهارستان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بوئین و میاندشت','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پیربکران','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تودشک','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تیران','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جندق','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جوزدان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جوشقان و کامو','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چادگان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چرمهین','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چمگردان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حبیب آباد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حسن آباد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حنا','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خالدآباد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خمینی شهر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خوانسار','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خور','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خورزوق','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'داران','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دامنه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'درچه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دستگرد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دهاقان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دهق','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دولت آباد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دیزیچه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رزوه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رضوانشهر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زاینده رود','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زرین شهر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زواره','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زیباشهر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سده لنجان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سفیدشهر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سگزی','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سمیرم','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شاهین شهر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهرضا','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'طالخونچه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'عسگران','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'علویجه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فرخی','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فریدونشهر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فلاورجان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فولادشهر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قمصر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قهجاورستان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قهدریجان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کاشان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کرکوند','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کلیشاد و سودرجان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کمشچه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کمه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کهریزسنگ','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوشک','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوهپایه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گرگاب','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گزبرخوار','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گلپایگان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گلدشت','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گلشهر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گوگد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لای بید','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مبارکه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مجلسی','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمدآباد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مشکات','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'منظریه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مهاباد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میمه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نائین','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نجف آباد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نصرآباد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نطنز','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نوش آباد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نیاسر','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نیک آباد','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هرند','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ورزنه','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ورنامخواست','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'وزوان','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ونک','CITY',4);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسارا','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اشتهارد','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تنکمان','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چهارباغ','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سعید آباد','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهر جدید هشتگرد','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'طالقان','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کرج','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کمال شهر','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوهسار','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گرمدره','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ماهدشت','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمدشهر','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مشکین دشت','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نظرآباد','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هشتگرد','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ارکواز','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایلام','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایوان','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آبدانان','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آسمان آباد','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بدره','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پهله','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'توحید','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چوار','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دره شهر','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دلگشا','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دهلران','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زرنه','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سراب باغ','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرابله','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صالح آباد','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لومار','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مهران','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مورموری','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'موسیان','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میمه','CITY',6);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'امام حسن','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'انارستان','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اهرم','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آب پخش','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آبدان','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'برازجان','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بردخون','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندردیر','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندردیلم','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرریگ','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرکنگان','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرگناوه','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بنک','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بوشهر','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تنگ ارم','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جم','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چغادک','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خارک','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خورموج','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دالکی','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دلوار','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ریز','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سعدآباد','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیراف','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شبانکاره','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شنبه','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'عسلویه','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کاکی','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کلمه','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نخل تقی','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'وحدتیه','CITY',7);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ارجمند','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسلامشهر','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اندیشه','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آبسرد','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آبعلی','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باغستان','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باقرشهر','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بومهن','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پاکدشت','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پردیس','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پیشوا','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تهران','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جوادآباد','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چهاردانگه','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حسن آباد','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دماوند','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دیزین','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهر ری','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رباط کریم','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رودهن','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شاهدشهر','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شریف آباد','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شمشک','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهریار','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صالح آباد','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صباشهر','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صفادشت','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فردوسیه','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فشم','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فیروزکوه','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قدس','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قرچک','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کهریزک','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کیلان','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گلستان','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لواسان','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ملارد','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میگون','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نسیم شهر','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نصیرآباد','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'وحیدیه','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ورامین','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اردل','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آلونی','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باباحیدر','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بروجن','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بلداجی','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بن','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جونقان','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چلگرد','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سامان','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سفیددشت','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سودجان','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سورشجان','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شلمزار','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهرکرد','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'طاقانک','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فارسان','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فرادنبه','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فرخ شهر','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کیان','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گندمان','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گهرو','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لردگان','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مال خلیفه','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ناغان','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نافچ','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نقنه','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هفشجان','CITY',9);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ارسک','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسدیه','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسفدن','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسلامیه','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آرین شهر','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آیسک','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بشرویه','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بیرجند','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حاجی آباد','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خضری دشت بیاض','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خوسف','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زهان','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرایان','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سربیشه','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سه قلعه','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شوسف','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'طبس','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فردوس','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قاین','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قهستان','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمدشهر','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مود','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نهبندان','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نیمبلوک','CITY',10);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'احمدآباد صولت','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'انابد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باجگیران','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باخرز','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بار','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بایگ','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بجستان','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بردسکن','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بیدخت','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تایباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تربت جام','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تربت حیدریه','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جغتای','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جنگل','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چاپشلو','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چکنه','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چناران','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خرو','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خلیل آباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خواف','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'داورزن','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'درگز','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'در رود','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دولت آباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رباط سنگ','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رشتخوار','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رضویه','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'روداب','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ریوش','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سبزوار','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرخس','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سفیدسنگ','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سلامی','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سلطان آباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سنگان','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شادمهر','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شاندیز','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ششتمد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهرآباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهرزو','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صالح آباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'طرقبه','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'عشق آباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فرهادگرد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فریمان','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فیروزه','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فیض آباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قاسم آباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قدمگاه','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قلندرآباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قوچان','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کاخک','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کاریز','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کاشمر','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کدکن','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کلات','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کندر','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گلمکان','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گناباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لطف آباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مزدآوند','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مشهد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ملک آباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نشتیفان','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نصرآباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نقاب','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نوخندان','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نیشابور','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نیل شهر','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'همت آباد','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'یونسی','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسفراین','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایور','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آشخانه','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بجنورد','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پیش قلعه','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تیتکانلو','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جاجرم','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حصارگرمخان','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'درق','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'راز','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سنخواست','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شوقان','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شیروان','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صفی آباد','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فاروج','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قاضی','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گرمه','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لوجلی','CITY',12);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اروندکنار','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'الوان','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'امیدیه','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اندیمشک','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اهواز','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایذه','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آبادان','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آغاجاری','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باغ ملک','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بستان','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرامام خمینی','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرماهشهر','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بهبهان','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ترکالکی','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جایزان','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چمران','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چویبده','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حر','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حسینیه','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حمزه','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حمیدیه','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خرمشهر','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دارخوین','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دزآب','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دزفول','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دهدز','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رامشیر','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رامهرمز','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رفیع','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زهره','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سالند','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سردشت','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سوسنگرد','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شادگان','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شاوور','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شرافت','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شوش','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شوشتر','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شیبان','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صالح شهر','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صفی آباد','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صیدون','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قلعه تل','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قلعه خواجه','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گتوند','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لالی','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مسجدسلیمان','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ملاثانی','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میانرود','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مینوشهر','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هفتگل','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هندیجان','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هویزه','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ویس','CITY',13);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ابهر','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ارمغان خانه','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آب بر','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چورزق','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حلب','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خرمدره','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دندی','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زرین آباد','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زرین رود','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زنجان','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سجاس','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سلطانیه','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سهرورد','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صائین قلعه','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قیدار','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گرماب','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ماه نشان','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هیدج','CITY',14);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'امیریه','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایوانکی','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آرادان','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بسطام','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بیارجمند','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دامغان','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'درجزین','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دیباج','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرخه','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سمنان','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شاهرود','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهمیرزاد','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کلاته خیج','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گرمسار','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مجن','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مهدی شهر','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میامی','CITY',15);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ادیمی','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسپکه','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایرانشهر','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بزمان','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بمپور','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بنت','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بنجار','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پیشین','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جالق','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چابهار','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خاش','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دوست محمد','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'راسک','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زابل','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زابلی','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زاهدان','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زهک','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سراوان','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرباز','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سوران','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیرکان','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'علی اکبر','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فنوج','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قصرقند','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کنارک','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گشت','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گلمورتی','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمدان','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمدآباد','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمدی','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میرجاوه','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نصرت آباد','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نگور','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نوک آباد','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نیک شهر','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هیدوچ','CITY',16);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اردکان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ارسنجان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'استهبان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اشکنان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'افزر','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اقلید','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'امام شهر','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اهل','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اوز','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایج','ایج',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایزدخواست','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آباده','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آباده طشک','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باب انار','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بالاده','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بنارویه','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بهمن','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بوانات','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بیرم','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بیضا','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جنت شهر','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جهرم','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جویم','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زرین دشت','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حسن آباد','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خان زنیان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خاوران','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خرامه','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خشت','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خنج','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خور','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'داراب','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'داریان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دبیران','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دژکرد','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دهرم','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دوبرجی','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رامجرد','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رونیز','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زاهدشهر','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زرقان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سده','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سروستان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سعادت شهر','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سورمق','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیدان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ششده','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهرپیر','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهرصدرا','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شیراز','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صغاد','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صفاشهر','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'علامرودشت','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فدامی','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فراشبند','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فسا','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فیروزآباد','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قائمیه','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قادرآباد','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قطب آباد','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قطرویه','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قیر','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کارزین (فتح آباد)','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کازرون','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کامفیروز','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کره ای','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کنارتخته','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوار','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گراش','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گله دار','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لار','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لامرد','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لپویی','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لطیفی','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مبارک آباددیز','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مرودشت','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مشکان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مصیری','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مهر','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میمند','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نوبندگان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نوجین','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نودان','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نورآباد','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نی ریز','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'وراوی','CITY',17);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ارداق','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسفرورین','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اقبالیه','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'الوند','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آبگرم','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آبیک','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آوج','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بوئین زهرا','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بیدستان','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تاکستان','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خاکعلی','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خرمدشت','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دانسفهان','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رازمیان','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سگزآباد','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیردان','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شال','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شریفیه','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ضیاآباد','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قزوین','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوهین','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمدیه','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمودآباد نمونه','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'معلم کلایه','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نرجه','CITY',18);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جعفریه','CITY',19);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دستجرد','CITY',19);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سلفچگان','CITY',19);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قم','CITY',19);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قنوات','CITY',19);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کهک','CITY',19);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آرمرده','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بابارشانی','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بانه','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بلبان آباد','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بوئین سفلی','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بیجار','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چناره','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دزج','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دلبران','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دهگلان','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دیواندره','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زرینه','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سروآباد','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سریش آباد','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سقز','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سنندج','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شویشه','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صاحب','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قروه','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کامیاران','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کانی دینار','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کانی سور','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مریوان','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'موچش','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'یاسوکند','CITY',20);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اختیارآباد','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ارزوئیه','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'امین شهر','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'انار','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اندوهجرد','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باغین','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بافت','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بردسیر','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بروات','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بزنجان','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بم','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بهرمان','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پاریز','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جبالبارز','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جوپار','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جوزم','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جیرفت','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چترود','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خاتون آباد','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خانوک','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خورسند','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'درب بهشت','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دهج','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رابر','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'راور','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'راین','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رفسنجان','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رودبار','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ریحان شهر','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زرند','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زنگی آباد','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زیدآباد','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیرجان','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهداد','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شهربابک','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صفائیه','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'عنبرآباد','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فاریاب','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فهرج','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قلعه گنج','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کاظم آباد','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کرمان','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کشکوئیه','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کهنوج','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوهبنان','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کیانشهر','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گلباف','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گلزار','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لاله زار','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ماهان','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمدآباد','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محی آباد','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مردهک','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مس سرچشمه','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'منوجان','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نجف شهر','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نرماشیر','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نظام شهر','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نگار','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نودژ','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هجدک','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'یزدان شهر','CITY',21);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ازگله','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسلام آباد غرب','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باینگان','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بیستون','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پاوه','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تازه آباد','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جوان رود','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حمیل','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ماهیدشت','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'روانسر','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرپل','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرمست','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سطر','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سنقر','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سومار','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شاهو','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صحنه','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قصرشیرین','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کرمانشاه','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کرندغرب','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کنگاور','کنگاور',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوزران','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گهواره','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گیلانغرب','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میان راهان','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نودشه','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نوسود','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هرسین','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هلشی','CITY',22);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'باشت','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پاتاوه','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چرام','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چیتاب','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دهدشت','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دوگنبدان','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دیشموک','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سوق','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سی سخت','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قلعه رئیسی','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گراب سفلی','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لنده','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لیکک','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مادوان','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مارگون','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'یاسوج','CITY',23);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'انبارآلوم','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اینچه برون','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آزادشهر','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آق قلا','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرترکمن','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرگز','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جلین','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خان ببین','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دلند','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رامیان','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرخنکلاته','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیمین شهر','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'علی آباد کتول','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فاضل آباد','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کردکوی','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کلاله','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گالیکش','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گرگان','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گمیش تپه','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گنبدکاووس','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مراوه','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مینودشت','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نگین شهر','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نوده خاندوز','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نوکنده','CITY',24);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ازنا','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اشترینان','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'الشتر','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'الیگودرز','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بروجرد','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پلدختر','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چالانچولان','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چغلوندی','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چقابل','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خرم آباد','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'درب گنبد','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دورود','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زاغه','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سپیددشت','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سراب دوره','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فیروزآباد','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کونانی','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوهدشت','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گراب','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'معمولان','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مومن آباد','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نورآباد','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ویسیان','CITY',25);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'احمدسرگوراب','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسالم','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اطاقور','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'املش','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آستارا','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آستانه اشرفیه','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بازار جمعه','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بره سر','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرانزلی','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پره سر','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تالش','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'توتکابن','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جیرنده','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چابکسر','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چاف و چمخاله','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چوبر','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حویق','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خشکبیجار','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خمام','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دیلمان','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رانکوه','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رحیم آباد','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رستم آباد','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رشت','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رضوانشهر','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رودبار','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رودبنه','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رودسر','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سنگر','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیاهکل','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شفت','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شلمان','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صومعه سرا','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فومن','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کلاچای','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوچصفهان','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کومله','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کیاشهر','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گوراب زرمیخ','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لاهیجان','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لشت نشا','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لنگرود','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لوشان','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لولمان','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لوندویل','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لیسار','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ماسال','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ماسوله','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مرجقل','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'منجیل','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'واجارگاه','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'امیرکلا','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ایزدشهر','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آلاشت','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آمل','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بابل','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بابلسر','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بلده','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بهشهر','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بهنمیر','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پل سفید','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تنکابن','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جویبار','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چالوس','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'چمستان','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خرم آباد','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خلیل شهر','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خوش رودپی','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دابودشت','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رامسر','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رستمکلا','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رویان','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رینه','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زرگرمحله','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زیرآب','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ساری','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرخرود','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سلمان شهر','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سورک','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شیرگاه','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شیرود','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'عباس آباد','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فریدونکنار','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فریم','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قائم شهر','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کتالم','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کلارآباد','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کلاردشت','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کله بست','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوهی خیل','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کیاسر','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کیاکلا','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گتاب','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گزنک','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گلوگاه','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محمودآباد','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مرزن آباد','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مرزیکلا','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نشتارود','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نکا','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نور','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نوشهر','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اراک','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آستانه','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'آشتیان','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پرندک','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تفرش','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'توره','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جاورسیان','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خشکرود','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خمین','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خنداب','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'داودآباد','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دلیجان','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رازقان','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زاویه','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ساروق','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ساوه','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سنجان','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شازند','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'غرق آباد','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فرمهین','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قورچی باشی','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کرهرود','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کمیجان','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مامونیه','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'محلات','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مهاجران','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میلاجرد','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نراق','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نوبران','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نیمور','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هندودر','CITY',28);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ابوموسی','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بستک','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرجاسک','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرچارک','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرخمیر','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرعباس','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بندرلنگه','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بیکا','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پارسیان','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تخت','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جناح','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حاجی آباد','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'درگهان','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دهبارز','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رویدر','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زیارتعلی','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سردشت','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سندرک','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سوزا','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سیریک','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فارغان','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فین','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قشم','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قلعه قاضی','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کنگ','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کوشکنار','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کیش','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گوهران','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میناب','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هرمز','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هشتبندی','CITY',29);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ازندریان','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اسدآباد','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'برزول','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بهار','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تویسرکان','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جورقان','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'جوکار','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دمق','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رزن','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زنگنه','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سامن','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سرکان','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شیرین سو','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'صالح آباد','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فامنین','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فرسفج','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فیروزان','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قروه درجزین','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قهاوند','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کبودر آهنگ','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گل تپه','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'گیان','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'لالجین','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مریانج','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ملایر','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نهاوند','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'همدان','CITY',30);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ابرکوه','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'احمدآباد','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اردکان','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'اشکذر','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بافق','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بفروئیه','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بهاباد','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تفت','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'حمیدیا','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'خضرآباد','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'دیهوک','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زارچ','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'شاهدیه','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'طبس','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'عقدا','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مروست','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مهردشت','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مهریز','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'میبد','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ندوشن','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'نیر','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'هرات','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'یزد','CITY',31);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پرند','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'فردیس','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'مارلیک','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'سادات شهر','CITY',27);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'زیباکنار','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'کردان','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'ساوجبلاغ','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'تهران دشت','CITY',5);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'قیامدشت','CITY',8);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'بینالود','CITY',11);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'پیربازار','CITY',26);
INSERT INTO public.lookup(id, content, look_up_type, parent_id) VALUES
 ((SELECT MAX(id)+1 FROM public.lookup),'رضوانشهر','CITY',31);

