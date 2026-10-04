package se.iths.christian.Bibliotekshanterare;

public record Member(int id, String name) {

    public Member {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Medlemmens namn får inte vara tomt.");
        }
        name = name.trim();
    }
}
