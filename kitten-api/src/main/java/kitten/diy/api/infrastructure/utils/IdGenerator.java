package kitten.diy.api.infrastructure.utils;

import lombok.RequiredArgsConstructor;

import java.util.random.RandomGenerator;

@RequiredArgsConstructor
public class IdGenerator {

    private static final String DEFAULT_CUSTOM_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";


    public String generate(int length,
                           RandomGenerator randomGenerator) {
        return generate(DEFAULT_CUSTOM_CHARACTERS, length, randomGenerator);
    }

    public String generate(String seed,
                           int length,
                           RandomGenerator randomGenerator) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = randomGenerator.nextInt(seed.length());
            char randomChar = seed.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }
}
