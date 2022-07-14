package me.kzv.olle.web.zone;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id") // 동등성 동일성 비교
@Builder
@AllArgsConstructor@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"city","province"}))
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String localNameOfCity;

    @Column(nullable = true)
    private String province;

    @Override
    public String toString() {
        return String.format("%s(%s)/%s", city, localNameOfCity, province);
    }

}
