package kitten.core.coredomain.utils;

import lombok.RequiredArgsConstructor;

import java.util.random.RandomGenerator;

@RequiredArgsConstructor
public class IdGenerator {

    private static final String DEFAULT_CUSTOM_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    private final RandomGenerator randomGenerator;


    public String generate(int length) {
        return generate(DEFAULT_CUSTOM_CHARACTERS, length);
    }

    public String generate(String seed,
                           int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = randomGenerator.nextInt(seed.length());
            char randomChar = seed.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
