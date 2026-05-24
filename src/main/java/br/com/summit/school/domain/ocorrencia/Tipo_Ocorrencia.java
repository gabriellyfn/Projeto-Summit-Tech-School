package br.com.summit.school.domain.ocorrencia;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Tipo_Ocorrencia")
@Table(name = "Tipo_Ocorrencia")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id_tipo_ocorrencia")
public class Tipo_Ocorrencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tipo_ocorrencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome_ocorrencia", length = 50, nullable = false)
    private Nome_Ocorrencia nome_Ocorrencia;

}
