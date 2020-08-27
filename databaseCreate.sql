drop table if exists Medico, Paciente, Consulta;
drop sequence if exists id_consulta;

create sequence id_consulta;

create table Paciente(
    cpf bigint,
    nome varchar(50),
    idade smallint,
    cidade varchar(50),
    descricao text,
    primary key (cpf)
);

create table Medico(
    cpf bigint,
    nome varchar(50),
    idade smallint,
    cidade varchar(50),
    especialidade text,
    primary key (cpf)
);

create table Consulta(
    id int,
    valor float,
    data date,
    diagnostico text,
    cpf_medico bigint,
    cpf_paciente bigint,
    primary key (id),
    foreign key (cpf_medico) references Medico(cpf) on update cascade on delete set null,
    foreign key (cpf_paciente) references Paciente(cpf) on update cascade on delete set null
);

