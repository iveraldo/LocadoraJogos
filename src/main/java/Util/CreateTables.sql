use GamesMania;
create table Usuario(
    id integer primary key auto_increment
    ,nome varchar(60) not null
    ,login varchar(60) not null 
    ,senha varchar(60) not null
    ,ativo tinyint not null
);

create table Vendedor(
    id integer primary key auto_increment
    ,cpf varchar(14) not null
    ,salario decimal(13,2) not null
    ,percentual_comissao integer not null
    ,data_admissao date not null
    ,data_demissao date
    ,id_usuario integer not null
    ,foreign key (id_usuario) references Usuario(id)
);

insert into usuario values(1, 'Nuno Leao', 'nuno', '123');
insert into vendedor values(1, '998.987.987-77', '2000', 1);

create table Idioma(
    id integer primary key auto_increment
    ,nome varchar(40) not null
    ,nome_original varchar(40) not null
    ,qtd_aprox_falantes_nativos bigint not null
    ,qtd_aprox_falantes_estrangeiros bigint not null
    ,familia_linguistica varchar(40) not null
    ,alfabeto varchar(40) not null
    ,qtd_paises_lingua_oficial int not null
    ,pais_origem varchar(40) not null
    ,data_origem date not null
);

create table Produtora(
    id integer primary key auto_increment
    ,nome varchar(60) not null
    ,data_fundacao date not null
    ,valor_renda_bruta decimal(13,2) 
    ,valor_lucros decimal(13,2) 
    ,mascote varchar(40) not null
    ,site_oficial varchar(40) not null
    ,cidade_sede varchar(40) not null
    ,pais_origem varchar(40) not null
    ,qtd_estudios integer not null
);

create table Plataforma(
    id integer primary key auto_increment
    ,descricao varchar(60) not null
    ,data_criacao date not null
    ,fabricante varchar(40) not null
    ,unidades_vendidas bigint not null
    ,numero_geracao integer not null
    ,midia varchar(20) not null
    ,site_oficial varchar(40) not null
    ,preco_lancamento decimal(13, 2)  not null
    ,sistema_operacional varchar(30) not null
);

create table Cliente(
    id integer primary key auto_increment
    ,nome varchar(60) not null
    ,cpf varchar(14) not null
    ,idade integer not null
    ,celular varchar(20) not null
    ,telefone varchar(20) not null
    ,email varchar(60) not null
    ,sexo varchar(9) not null
    ,data_cadastro date not null
);

create table Endereco(
    id integer primary key auto_increment
    ,logradouro varchar(60) not null
    ,numero integer not null
    ,complemento varchar(50) not null
    ,bairro varchar(50) not null
    ,cidade varchar(50) not null
    ,estado char(2) not null
    ,cep varchar(9) not null
    ,data_criacao date not null
    ,obs varchar(200) not null
    ,id_cliente integer not null
    ,foreign key (id_cliente) references Cliente(id)
);