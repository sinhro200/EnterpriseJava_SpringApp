package org.sinhro.ForeignLanguageCourses.tools;

import lombok.ToString;

import java.util.Random;

public class NamesDataBase {


    private static String[] menNames = {
        "Иван",
        "Дмитрий",
        "Олег",
        "Василий",
        "Кирилл",
        "Павел",
        "Александр",
        "Юрий",
        "Алексей",
        "Владимир",
    };
    private static String[] menSurnames = {
        "Иванов",
        "Смирнов",
        "Кузнецов",
        "Попов",
        "Васильев",
        "Петров",
        "Соколов",
        "Михайлов",
        "Новиков",
    };
    private static String[] menPatronymics = {
        "Дмитриевич",
        "Станиславович",
        "Владиславович",
        "Кириллович",
        "Динисович",
        "Олегович",
        "Юрьевич",
        "Александрович",
        "Иванович",
    };
    private static String[] womenNames = {
        "Юлия",
        "Анна",
        "Екатерина",
        "Елизавета",
        "Полина",
        "София",
        "Анастасия",
        "Мария",
        "Виктория"
    };
    private static String[] womenSurnames = {
        "Иванова",
        "Смирнова",
        "Кузнецова",
        "Попова",
        "Васильева",
        "Петрова",
        "Соколова",
        "Михайлова",
        "Новикова",
    };

    private static String[] womenPatronymics = {
        "Дмитриевна",
        "Станиславовна",
        "Владиславовна",
        "Кирилловна",
        "Динисовна",
        "Олеговна",
        "Юрьевна",
        "Александровна",
        "Ивановна",
    };

    public static FullName getRandomFullMenName(Random random) {
        return new FullName(
            menNames[random.nextInt(menNames.length)],
            menSurnames[random.nextInt(menSurnames.length)],
            menPatronymics[random.nextInt(menPatronymics.length)]
        );
    }

    public static FullName getRandomFullWomenName(Random random) {
        return new FullName(
            womenNames[random.nextInt(womenNames.length)],
            womenSurnames[random.nextInt(womenSurnames.length)],
            womenPatronymics[random.nextInt(womenPatronymics.length)]
        );
    }

    public static FullName getRandomFullName(Random random) {
        if (random.nextInt() % 2 == 1)
            return getRandomFullMenName(random);
        else
            return getRandomFullWomenName(random);
    }

    @ToString
    public static class FullName {
        private String name;
        private String surname;
        private String patronymics;

        public FullName(String name, String surname, String patronymics) {
            this.name = name;
            this.surname = surname;
            this.patronymics = patronymics;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public String getPatronymics() {
            return patronymics;
        }

    }

}
