-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 10 Sty 2023, 13:01
-- Wersja serwera: 10.4.27-MariaDB
-- Wersja PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `e-dziennik`
--
z
-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `allocation`
--

CREATE TABLE `allocation` (
  `class_id` int(8) NOT NULL,
  `student_id` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `allocation`
--

INSERT INTO `allocation` (`class_id`, `student_id`) VALUES
(1, 14);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `announcements`
--

CREATE TABLE `announcements` (
  `announcement_id` int(8) NOT NULL,
  `title` varchar(20) NOT NULL,
  `message` varchar(200) NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `announcements`
--

INSERT INTO `announcements` (`announcement_id`, `title`, `message`, `start`, `end`) VALUES
(1, 'Nowy rok!', 'Witamy wszystkich uczniów w nowym roku!', '2022-12-26', '2023-01-30'),
(2, 'Test', 'Testowa treść', '2023-01-02', '2023-01-16'),
(3, 'Nowe ogłoszenie', 'Nowe testowe ogłoszenie przy ogólnym, sprawdzaniu systemów', '2023-01-02', '2023-01-30');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `class`
--

CREATE TABLE `class` (
  `class_id` int(8) NOT NULL,
  `supervisor_id` int(8) NOT NULL,
  `year` int(1) NOT NULL,
  `name` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `class`
--

INSERT INTO `class` (`class_id`, `supervisor_id`, `year`, `name`) VALUES
(1, 15, 1, 'A'),
(2, 15, 2, 'A');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `classroom`
--

CREATE TABLE `classroom` (
  `classroom_id` int(8) NOT NULL,
  `number` int(3) NOT NULL,
  `floor` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `classroom`
--

INSERT INTO `classroom` (`classroom_id`, `number`, `floor`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 12, 2),
(4, 11, 2);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `final_grade`
--

CREATE TABLE `final_grade` (
  `grade_id` int(8) NOT NULL,
  `subject_id` int(8) NOT NULL,
  `class_id` int(8) NOT NULL,
  `student_id` int(8) NOT NULL,
  `grade` int(1) NOT NULL,
  `type` enum('1 sem','2 sem','koniec roku') NOT NULL,
  `status` enum('brak','proponowana','ostateczna') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `grade`
--

CREATE TABLE `grade` (
  `grade_id` int(8) NOT NULL,
  `subject_id` int(8) NOT NULL,
  `task_type_id` int(8) NOT NULL,
  `student_id` int(8) NOT NULL,
  `grade` int(1) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `improved_grade_id` int(8) DEFAULT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `grade`
--

INSERT INTO `grade` (`grade_id`, `subject_id`, `task_type_id`, `student_id`, `grade`, `description`, `improved_grade_id`, `date`) VALUES
(12, 3, 1, 14, 4, 'Sprawdzian z średniowiecza', NULL, '2023-01-02');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `group_message`
--

CREATE TABLE `group_message` (
  `chat_id` int(8) NOT NULL,
  `user_id` int(8) NOT NULL,
  `last_read` int(8) DEFAULT NULL,
  `status` enum('admin','użytkownik') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `group_message`
--

INSERT INTO `group_message` (`chat_id`, `user_id`, `last_read`, `status`) VALUES
(1, 14, 2, NULL),
(2, 14, 5, NULL),
(1, 15, 2, NULL),
(2, 15, 4, NULL),
(2, 17, NULL, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `group_name`
--

CREATE TABLE `group_name` (
  `chat_id` int(8) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `group_name`
--

INSERT INTO `group_name` (`chat_id`, `name`) VALUES
(1, 'Student chat'),
(2, 'Parent, teacher chat');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lesson_calendar`
--

CREATE TABLE `lesson_calendar` (
  `lesson_id` int(8) NOT NULL,
  `plan_id` int(8) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `lesson_calendar`
--

INSERT INTO `lesson_calendar` (`lesson_id`, `plan_id`, `date`) VALUES
(1, 1, '2023-01-16');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lesson_plan`
--

CREATE TABLE `lesson_plan` (
  `plan_id` int(8) NOT NULL,
  `class_id` int(8) NOT NULL,
  `subject_id` int(8) NOT NULL,
  `teacher_id` int(8) NOT NULL,
  `classroom_id` int(8) NOT NULL,
  `week_day` enum('poniedzialek','wtorek','sroda','czwartek','piatek','sobota','niedziela') NOT NULL,
  `start` time NOT NULL,
  `end` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `lesson_plan`
--

INSERT INTO `lesson_plan` (`plan_id`, `class_id`, `subject_id`, `teacher_id`, `classroom_id`, `week_day`, `start`, `end`) VALUES
(1, 1, 3, 15, 1, 'poniedzialek', '08:00:00', '08:45:00'),
(2, 1, 1, 15, 2, 'wtorek', '08:00:00', '08:45:00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `messages`
--

CREATE TABLE `messages` (
  `message_id` int(8) NOT NULL,
  `chat_id` int(8) NOT NULL,
  `user_id` int(8) NOT NULL,
  `date` date NOT NULL,
  `message` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `messages`
--

INSERT INTO `messages` (`message_id`, `chat_id`, `user_id`, `date`, `message`) VALUES
(1, 1, 15, '2023-01-09', 'Witam serdecznie'),
(2, 1, 14, '2023-01-09', 'Dzień dobry'),
(3, 2, 14, '2023-01-09', 'Dzień dobry'),
(4, 2, 15, '2023-01-09', 'Witam'),
(5, 2, 14, '2023-01-09', 'Witam');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `notes`
--

CREATE TABLE `notes` (
  `note_id` int(8) NOT NULL,
  `student_id` int(8) NOT NULL,
  `teacher_id` int(8) NOT NULL,
  `date` date NOT NULL,
  `title` varchar(20) NOT NULL,
  `message` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `notes`
--

INSERT INTO `notes` (`note_id`, `student_id`, `teacher_id`, `date`, `title`, `message`) VALUES
(3, 14, 15, '2023-01-06', 'Wzorowe zachowanie', 'Czynny udział w apelu, z okazji święta');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `parent_children`
--

CREATE TABLE `parent_children` (
  `parent_id` int(10) NOT NULL,
  `child_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `parent_children`
--

INSERT INTO `parent_children` (`parent_id`, `child_id`) VALUES
(17, 14);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `presence`
--

CREATE TABLE `presence` (
  `student_id` int(8) NOT NULL,
  `lesson_id` int(8) NOT NULL,
  `state` enum('obecny','spozniony','nieobecny','usprawiedliwiony','zwolniony','brak') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `school`
--

CREATE TABLE `school` (
  `school_id` int(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `school`
--

INSERT INTO `school` (`school_id`, `name`, `address`) VALUES
(1, 'Szkoła podstawowa im \"KEN\"', 'ul. Szkolna Kraków');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `subject`
--

CREATE TABLE `subject` (
  `subject_id` int(8) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `subject`
--

INSERT INTO `subject` (`subject_id`, `name`) VALUES
(1, 'Język Polski'),
(2, 'Metematyka'),
(3, 'Historia');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `substitutions`
--

CREATE TABLE `substitutions` (
  `sub_teacher_id` int(8) NOT NULL,
  `lesson_id` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `task`
--

CREATE TABLE `task` (
  `task_type_id` int(8) NOT NULL,
  `lesson_id` int(8) NOT NULL,
  `description` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `task`
--

INSERT INTO `task` (`task_type_id`, `lesson_id`, `description`) VALUES
(2, 1, 'Szybka kartkówka');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `task_type`
--

CREATE TABLE `task_type` (
  `task_type_id` int(8) NOT NULL,
  `name` varchar(20) NOT NULL,
  `weight` int(3) NOT NULL,
  `teacher_id` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `task_type`
--

INSERT INTO `task_type` (`task_type_id`, `name`, `weight`, `teacher_id`) VALUES
(1, 'Sprawdzian', 1, NULL),
(2, 'Kartkówka', 1, NULL);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `user_id` int(8) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `mail` varchar(20) DEFAULT NULL,
  `phone_number` int(9) DEFAULT NULL,
  `position` enum('admin','sekretariat','nauczyciel','rodzic','uczen') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`user_id`, `login`, `password`, `name`, `surname`, `mail`, `phone_number`, `position`) VALUES
(13, 'firns', 'firns', 'firns', 'firns', 'firns@rfnq.htr', 111222333, 'admin'),
(14, 'xyzijsy', 'xyzijsy', 'xyzijsy', 'xyzijsy', 'xyzijsy@rfnq.htr', 222333222, 'uczen'),
(15, 'yjfhmjw', 'yjfhmjw', 'yjfhmjw', 'yjfhmjw', 'yjfhmjw@rfnq.htr', 333222333, 'nauczyciel'),
(17, 'ufwjsy', 'ufwjsy', 'ufwjsy', 'ufwjsy', 'ufwjsy@rfnq.htr', 444555444, 'rodzic');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `allocation`
--
ALTER TABLE `allocation`
  ADD PRIMARY KEY (`class_id`,`student_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indeksy dla tabeli `announcements`
--
ALTER TABLE `announcements`
  ADD PRIMARY KEY (`announcement_id`);

--
-- Indeksy dla tabeli `class`
--
ALTER TABLE `class`
  ADD PRIMARY KEY (`class_id`),
  ADD KEY `supervisor_id` (`supervisor_id`);

--
-- Indeksy dla tabeli `classroom`
--
ALTER TABLE `classroom`
  ADD PRIMARY KEY (`classroom_id`);

--
-- Indeksy dla tabeli `final_grade`
--
ALTER TABLE `final_grade`
  ADD PRIMARY KEY (`grade_id`),
  ADD KEY `subject_id` (`subject_id`),
  ADD KEY `class_id` (`class_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indeksy dla tabeli `grade`
--
ALTER TABLE `grade`
  ADD PRIMARY KEY (`grade_id`),
  ADD KEY `subject_id` (`subject_id`),
  ADD KEY `task_type_id` (`task_type_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indeksy dla tabeli `group_message`
--
ALTER TABLE `group_message`
  ADD PRIMARY KEY (`user_id`,`chat_id`),
  ADD KEY `last_read` (`last_read`),
  ADD KEY `group_id` (`chat_id`) USING BTREE,
  ADD KEY `user_id` (`user_id`,`chat_id`) USING BTREE;

--
-- Indeksy dla tabeli `group_name`
--
ALTER TABLE `group_name`
  ADD PRIMARY KEY (`chat_id`) USING BTREE;

--
-- Indeksy dla tabeli `lesson_calendar`
--
ALTER TABLE `lesson_calendar`
  ADD PRIMARY KEY (`lesson_id`),
  ADD KEY `plan_id` (`plan_id`);

--
-- Indeksy dla tabeli `lesson_plan`
--
ALTER TABLE `lesson_plan`
  ADD PRIMARY KEY (`plan_id`),
  ADD KEY `class_id` (`class_id`),
  ADD KEY `subject_id` (`subject_id`),
  ADD KEY `teacher_id` (`teacher_id`),
  ADD KEY `classroom_id` (`classroom_id`);

--
-- Indeksy dla tabeli `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `chat_id` (`chat_id`);

--
-- Indeksy dla tabeli `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`note_id`),
  ADD KEY `teacher_id` (`teacher_id`),
  ADD KEY `student_id` (`student_id`);

--
-- Indeksy dla tabeli `parent_children`
--
ALTER TABLE `parent_children`
  ADD PRIMARY KEY (`parent_id`,`child_id`),
  ADD KEY `child_id` (`child_id`);

--
-- Indeksy dla tabeli `presence`
--
ALTER TABLE `presence`
  ADD PRIMARY KEY (`student_id`,`lesson_id`),
  ADD KEY `lesson_id` (`lesson_id`);

--
-- Indeksy dla tabeli `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`subject_id`);

--
-- Indeksy dla tabeli `substitutions`
--
ALTER TABLE `substitutions`
  ADD PRIMARY KEY (`sub_teacher_id`,`lesson_id`),
  ADD KEY `class_id` (`lesson_id`);

--
-- Indeksy dla tabeli `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`task_type_id`,`lesson_id`),
  ADD KEY `lesson_id` (`lesson_id`);

--
-- Indeksy dla tabeli `task_type`
--
ALTER TABLE `task_type`
  ADD PRIMARY KEY (`task_type_id`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `class`
--
ALTER TABLE `class`
  MODIFY `class_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT dla tabeli `final_grade`
--
ALTER TABLE `final_grade`
  MODIFY `grade_id` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `grade`
--
ALTER TABLE `grade`
  MODIFY `grade_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT dla tabeli `notes`
--
ALTER TABLE `notes`
  MODIFY `note_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT dla tabeli `subject`
--
ALTER TABLE `subject`
  MODIFY `subject_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `allocation`
--
ALTER TABLE `allocation`
  ADD CONSTRAINT `allocation_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`),
  ADD CONSTRAINT `allocation_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `allocation_ibfk_3` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`);

--
-- Ograniczenia dla tabeli `class`
--
ALTER TABLE `class`
  ADD CONSTRAINT `class_ibfk_1` FOREIGN KEY (`supervisor_id`) REFERENCES `users` (`user_id`);

--
-- Ograniczenia dla tabeli `final_grade`
--
ALTER TABLE `final_grade`
  ADD CONSTRAINT `final_grade_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  ADD CONSTRAINT `final_grade_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`),
  ADD CONSTRAINT `final_grade_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `users` (`user_id`);

--
-- Ograniczenia dla tabeli `grade`
--
ALTER TABLE `grade`
  ADD CONSTRAINT `grade_ibfk_1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  ADD CONSTRAINT `grade_ibfk_2` FOREIGN KEY (`task_type_id`) REFERENCES `task_type` (`task_type_id`),
  ADD CONSTRAINT `grade_ibfk_3` FOREIGN KEY (`student_id`) REFERENCES `users` (`user_id`);

--
-- Ograniczenia dla tabeli `group_message`
--
ALTER TABLE `group_message`
  ADD CONSTRAINT `group_message_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `group_message_ibfk_2` FOREIGN KEY (`chat_id`) REFERENCES `group_name` (`chat_id`),
  ADD CONSTRAINT `group_message_ibfk_3` FOREIGN KEY (`last_read`) REFERENCES `messages` (`message_id`),
  ADD CONSTRAINT `group_message_ibfk_4` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Ograniczenia dla tabeli `lesson_calendar`
--
ALTER TABLE `lesson_calendar`
  ADD CONSTRAINT `lesson_calendar_ibfk_1` FOREIGN KEY (`plan_id`) REFERENCES `lesson_plan` (`plan_id`);

--
-- Ograniczenia dla tabeli `lesson_plan`
--
ALTER TABLE `lesson_plan`
  ADD CONSTRAINT `lesson_plan_ibfk_1` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`),
  ADD CONSTRAINT `lesson_plan_ibfk_2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`),
  ADD CONSTRAINT `lesson_plan_ibfk_3` FOREIGN KEY (`teacher_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `lesson_plan_ibfk_4` FOREIGN KEY (`classroom_id`) REFERENCES `classroom` (`classroom_id`);

--
-- Ograniczenia dla tabeli `messages`
--
ALTER TABLE `messages`
  ADD CONSTRAINT `messages_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `messages_ibfk_3` FOREIGN KEY (`chat_id`) REFERENCES `group_name` (`chat_id`);

--
-- Ograniczenia dla tabeli `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `notes_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `users` (`user_id`);

--
-- Ograniczenia dla tabeli `parent_children`
--
ALTER TABLE `parent_children`
  ADD CONSTRAINT `parent_children_ibfk_1` FOREIGN KEY (`parent_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `parent_children_ibfk_2` FOREIGN KEY (`child_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `parent_children_ibfk_3` FOREIGN KEY (`parent_id`) REFERENCES `users` (`user_id`);

--
-- Ograniczenia dla tabeli `presence`
--
ALTER TABLE `presence`
  ADD CONSTRAINT `presence_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `presence_ibfk_3` FOREIGN KEY (`lesson_id`) REFERENCES `lesson_calendar` (`lesson_id`);

--
-- Ograniczenia dla tabeli `substitutions`
--
ALTER TABLE `substitutions`
  ADD CONSTRAINT `substitutions_ibfk_1` FOREIGN KEY (`sub_teacher_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `substitutions_ibfk_2` FOREIGN KEY (`lesson_id`) REFERENCES `lesson_calendar` (`lesson_id`);

--
-- Ograniczenia dla tabeli `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`task_type_id`) REFERENCES `task_type` (`task_type_id`),
  ADD CONSTRAINT `task_ibfk_2` FOREIGN KEY (`lesson_id`) REFERENCES `lesson_calendar` (`lesson_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
