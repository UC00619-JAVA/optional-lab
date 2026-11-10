void main() {
    UserDirectory directory = new UserDirectory();

    // Part A - predict the output before running!
    IO.println("1) orElse, user exists:");
    User u1 = directory.findByEmail("ana@mail.com").orElse(UserDirectory.createGuest());
    IO.println("  result: " + u1.name());

    IO.println("2) orElseGet, user exists:");
    User u2 = directory.findByEmail("ana@mail.com").orElseGet(() -> UserDirectory.createGuest());
    IO.println("  result: " + u2.name());

    IO.println("3) orElseGet, user missing:");
    User u3 = directory.findByEmail("joao@mail.com").orElseGet(UserDirectory::createGuest);
    IO.println("  result: " + u3.name());

    // Part B - using the Optional
    IO.println("Name of rui@mail.com: " + directory.getName("rui@mail.com"));
    IO.println("Name of x@mail.com: " + directory.getName("x@mail.com"));

    directory.findByEmail("ana@mail.com")
            .ifPresent(user -> IO.println("Welcome back, " + user.name() + "!"));

    try {
        directory.requireUser("x@mail.com");
    } catch (UserNotFoundException e) {
        IO.println("Error: " + e.getMessage());
    }
}
