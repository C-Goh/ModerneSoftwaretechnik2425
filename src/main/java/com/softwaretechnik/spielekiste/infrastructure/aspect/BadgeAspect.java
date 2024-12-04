
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import com.softwaretechnik.spielekiste.user.domain.repository.UserRepository;


@Aspect
public class BadgeAspect {


    private UserRepository userRepository;

    public BadgeAspect() {
    }

    // Setter for dependency injection
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @After("execution(* com.softwaretechnik.spielekiste.game.service.GameService.endGame(..))")
    public void awardBadge() {
        if (userRepository != null) {
            // Save game points logic
            System.out.println("Awarding badge for user...");
        } else {
            System.out.println("UserRepository not initialized.");
        }
    }
    
}
