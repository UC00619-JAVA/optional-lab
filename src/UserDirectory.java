import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDirectory {

  private final Map<String, User> users = new HashMap<>();

  public UserDirectory() {
    users.put("ana@mail.com", new User("ana@mail.com", "Ana"));
    users.put("rui@mail.com", new User("rui@mail.com", "Rui"));
  }

  // the return type says it: the user may not exist
  public Optional<User> findByEmail(String email) {
    return Optional.ofNullable(users.get(email));
  }

  // map: transform the value if it exists; orElse: fixed default
  public String getName(String email) {
    return findByEmail(email)
        .map(User::name)
        .orElse("Unknown");
  }

  // orElseThrow: no user -> exception
  public User requireUser(String email) {
    return findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException(email));
  }

  // simulates an expensive operation (a database INSERT, an API call...)
  public static User createGuest() {
    System.out.println("  >> creating guest user...");
    return new User("guest@mail.com", "Guest");
  }
}
