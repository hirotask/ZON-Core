CREATE DATABASE IF NOT EXISTS zonkills;

CREATE TABLE IF NOT EXISTS zonkills.ms_players (
    mp_id int(11) auto_increment primary key,
    mp_name varchar(36) not null,
    mp_uuid varchar(36) not null
);

CREATE TABLE IF NOT EXISTS zonkills.dt_player_kills (
	dpk_id int(11) auto_increment primary key,
	mp_id int(11) not null,
	dpk_value int(11) not null default 0,
	created_at timestamp default now(),
	foreign key fk_dpk_mp_id(mp_id) references ms_players(mp_id)
);

CREATE TABLE IF NOT EXISTS zonkills.dt_status_points (
	dsp_id int(11) auto_increment primary key,
	mp_id int(11) not null,
	dsp_value int(11) not null default 0,
	created_at timestamp default now(),
	foreign key fk_dsp_mp_id(mp_id) references ms_players(mp_id)
);

CREATE TABLE IF NOT EXISTS zonkills.dt_strength (
	ds_id int(11) auto_increment primary key,
	mp_id int(11) not null,
	ds_strength int(11) not null default 0,
	created_at timestamp default now(),
	foreign key fk_ds_mp_id(mp_id) references ms_players(mp_id)
);

CREATE TABLE IF NOT EXISTS zonkills.dt_hp (
	dh_id int(11) auto_increment primary key,
	mp_id int(11) not null,
	dh_hp int(11) not null default 0,
	created_at timestamp default now(),
	foreign key fk_dh_mp_id(mp_id) references ms_players(mp_id)
);

CREATE TABLE IF NOT EXISTS zonkills.dt_hp_regen (
	dhr_id int(11) auto_increment primary key,
	mp_id int(11) not null,
	dhr_hp_regen int(11) not null default 0,
	created_at timestamp default now(),
	foreign key fk_dhr_mp_id(mp_id) references ms_players(mp_id)
);

CREATE TABLE IF NOT EXISTS zonkills.dt_mp (
	dm_id int(11) auto_increment primary key,
	mp_id int(11) not null,
	dm_mp int(11) not null default 0,
	created_at timestamp default now(),
	foreign key fk_dm_mp_id(mp_id) references ms_players(mp_id)
);

CREATE TABLE IF NOT EXISTS zonkills.dt_mp_regen (
	dmr_id int(11) auto_increment primary key,
	mp_id int(11) not null,
	dmr_mp_regen int(11) not null default 0,
	created_at timestamp default now(),
	foreign key fk_dmr_mp_id(mp_id) references ms_players(mp_id)
);