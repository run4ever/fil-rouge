insert into app_user(email,firstname,lastname,birthday_date,password,role) values ('superman@world.com','super','man','2000-01-01','superman','ROLE_USER');
insert into app_user(email,firstname,lastname,birthday_date,password,role) values ('youssra@tcl.com','Youssra','TIGE','2000-01-01','superman','ROLE_ADMIN');
insert into app_user(email,firstname,lastname,birthday_date,password,role) values ('arnaud@tcl.com','Arnaud','CHENIER','2000-01-01','superman','ROLE_ADMIN');
insert into app_user(email,firstname,lastname,birthday_date,password,role) values ('fabien@tcl.com','Fabien','LAURETTE','2000-01-01','superman','ROLE_ADMIN');
truncate table movie CASCADE;
insert into movie(imdb_id, title, description, image_url, category, actors, release_date, duration, average_rating, number_of_votes) values('tt1856101','Blade Runner 2049','Young Blade Runner discovery of a long-buried secret leads him to track down former Blade Runner Rick Deckard, who''s been missing for thirty years.', 'https://m.media-amazon.com/images/M/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_SX300.jpg', 'ACTION', 'Ryan Gosling, Dave Bautista, Robin Wright, Mark Arnold', '2017-01-01', 164, 8.0, 463964);
insert into movie(imdb_id, title, description, image_url, category, actors, release_date, duration, average_rating, number_of_votes) values('tt0083658','Blade Runner', 'A blade runner must pursue and terminate four replicants who stole a ship in space, and have returned to Earth to find their creator.', 'https://m.media-amazon.com/images/M/MV5BNzQzMzJhZTEtOWM4NS00MTdhLTg0YjgtMjM4MDRkZjUwZDBlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg', 'ACTION', 'Harrison Ford, Rutger Hauer, Sean Young, Edward James Olmos', '1982-01-01', 117, 8.1, 695428);
insert into serie(imdb_id,title,description,start_year,end_year,number_of_season,number_of_episode,status_serie,category) values('tt00test000','friends','Hi Firends','1990','2000','10','150','FINISH','ANIMATION');
insert into serie(imdb_id,title,description,start_year,end_year,number_of_season,number_of_episode,status_serie,category) values('tt00test999','Game of thrones','Neuf familles nobles rivalisent pour le contrôle du Trône de Fer dans les sept royaumes de Westeros','2005',null,'8','150','FINISH','ANIMATION');
insert into serie_viewing(app_user_jpa_user_id,serie_jpa_serie_id,status,current_season,current_episode,date_last_action) values (1,1,'WATCHED',5,2,'2021-03-01 21:36:15.108');




