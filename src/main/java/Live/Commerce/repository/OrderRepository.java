package Live.Commerce.repository;

import Live.Commerce.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        //member는 many라서 join을 해줘야하나보네. 아무래도 전반적인 정리가 더 필요해보인다
        return em.createQuery("select o from Order o join o.member m", Order.class)
                .getResultList();
    }

}