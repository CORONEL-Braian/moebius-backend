package app.moebius.domain.entity.role;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "subscription")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSQLEnumType.class
)
public class Subscription2 {

    @Id @GeneratedValue @Column(name = "subscription_uuid", updatable = false)
    private UUID id = UUID.randomUUID();

    @Enumerated(EnumType.STRING)
    @Column(name = "status_subscription")
    @Type( type = "pgsql_enum" )
    private StatusSubscription status = StatusSubscription.PREMIUM;

}