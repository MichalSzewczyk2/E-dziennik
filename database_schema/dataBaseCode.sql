-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 02 Sty 2023, 14:39
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

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `allocation`
--

CREATE TABLE `allocation` (
  `class_id` int(8) NOT NULL,
  `student_id` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(1, 'Nowy rok!', 'Witamy wszystkich uczniów w nowym roku!', '2022-12-26', '2023-01-30');

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

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `classroom`
--

CREATE TABLE `classroom` (
  `classroom_id` int(8) NOT NULL,
  `number` int(3) NOT NULL,
  `floor` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `group_message`
--

CREATE TABLE `group_message` (
  `chat_id` int(8) NOT NULL,
  `user_id` int(8) NOT NULL,
  `last_read` int(8) NOT NULL,
  `status` enum('admin','użytkownik') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `group_name`
--

CREATE TABLE `group_name` (
  `chat_id` int(8) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `lesson_calendar`
--

CREATE TABLE `lesson_calendar` (
  `lesson_id` int(8) NOT NULL,
  `plan_id` int(8) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(1, 'admin', 'admin', 'Admin', 'Admin', NULL, NULL, 'admin'),
(2, 'student', 'student', 'Jan', 'Kowalski', 'student@mail.com', 111222333, 'uczen'),
(3, 'pnowak', '12345', 'Piotr', 'Nowak', 'PNowak@mail.com', 333222333, 'uczen');

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
  MODIFY `class_id` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `final_grade`
--
ALTER TABLE `final_grade`
  MODIFY `grade_id` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `grade`
--
ALTER TABLE `grade`
  MODIFY `grade_id` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `subject`
--
ALTER TABLE `subject`
  MODIFY `subject_id` int(8) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
