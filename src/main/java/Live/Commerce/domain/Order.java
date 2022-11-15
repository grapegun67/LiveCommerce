package Live.Commerce.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order_item_id")
    private List<OrderItem> orderItems;
}
