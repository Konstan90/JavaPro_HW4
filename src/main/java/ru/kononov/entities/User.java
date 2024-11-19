package ru.kononov.entities;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {
    private int id;
    private final String name;
}
