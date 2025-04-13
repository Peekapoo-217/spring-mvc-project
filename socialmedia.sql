-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 19, 2025 at 10:05 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `socialmedia`
--

-- --------------------------------------------------------

--
-- Table structure for table `follows`
--

CREATE TABLE `follows` (
  `follower_id` int(11) NOT NULL,
  `following_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `follows`
--

INSERT INTO `follows` (`follower_id`, `following_id`, `created_at`) VALUES
(1, 1, '2025-03-19 08:33:04');

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `body` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` enum('DRAFTED','POSTED') NOT NULL DEFAULT 'DRAFTED',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `posts`
--

INSERT INTO `posts` (`id`, `title`, `body`, `user_id`, `status`, `created_at`) VALUES
(1, 'Chào mừng đến với Social Network!', 'Chúng tôi rất vui mừng khi bạn tham gia. Hãy chia sẻ những khoảnh khắc tuyệt vời cùng mọi người!', 2, 'POSTED', '2025-03-19 08:32:46'),
(2, 'Lời nhắc nhở mỗi ngày', 'Hãy nhớ dành thời gian cho bản thân. Sức khỏe và tinh thần luôn là điều quan trọng nhất!', 1, 'POSTED', '2025-03-19 08:32:46'),
(3, 'Mẹo nhỏ giúp tập trung', 'Tắt thông báo điện thoại và đặt ra khung giờ làm việc không bị gián đoạn. Bạn sẽ thấy năng suất hơn!', 2, 'POSTED', '2025-03-19 08:32:46'),
(4, 'Hôm nay bạn đã cười chưa?', 'Dù bận rộn thế nào, đừng quên nở nụ cười. Một nụ cười có thể thay đổi cả ngày của bạn đấy!', 2, 'POSTED', '2025-03-19 08:32:46'),
(5, 'Sách hay nên đọc', 'Nếu bạn đang tìm sách, tôi khuyên bạn nên thử đọc \"7 Thói quen của người thành đạt\" – rất bổ ích!', 2, 'POSTED', '2025-03-19 08:32:46'),
(6, 'Quote hay', '“Hành trình ngàn dặm bắt đầu từ một bước chân.” – Lao Tzu', 2, 'POSTED', '2025-03-19 08:32:46');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(20) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','USER') NOT NULL DEFAULT 'USER',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `role`, `created_at`) VALUES
(1, 'hungdev', '$2a$10$YRV.XpuGOVNgNKwOK/G5ieniDj0NHDsv8k9SBrRgrtbjgsKUov3B6', 'ADMIN', '2025-03-19 08:28:54'),
(2, 'caubelauga', '$2a$10$ZYmp6NRHdYnv/RURBCF66eumfJ4EyHOtTFbR7XgqAMy06kz6c.3c.', 'USER', '2025-03-19 08:29:18'),
(3, 'danh', '$2a$10$FKy4kPCyfbVSf7JuhwailOpPEZQ1x2k2NVnaVn.eLQeZYHkWfiJqS', 'USER', '2025-03-19 08:47:19'),
(4, 'hongnhung', '$2a$10$NQA4RtDRKuXIo7h4IFv0n.oM/.fjSC7sxYc/rdn9wDeVpcCRkNzxu', 'USER', '2025-03-19 09:02:03'),
(5, 'huynh', '$2a$10$DjmahA55GGgXaaQMpcaP/OG3SF2lPjWkfydJJUhPm3/GewFA.jdN2', 'USER', '2025-03-19 09:02:10');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `follows`
--
ALTER TABLE `follows`
  ADD PRIMARY KEY (`follower_id`,`following_id`),
  ADD KEY `followed_user_id` (`following_id`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `follows`
--
ALTER TABLE `follows`
  ADD CONSTRAINT `follows_ibfk_1` FOREIGN KEY (`follower_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `follows_ibfk_2` FOREIGN KEY (`following_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `posts`
--
ALTER TABLE `posts`
  ADD CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
