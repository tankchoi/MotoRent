-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 14, 2025 lúc 05:40 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `motorent`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `audit_logs`
--

CREATE TABLE `audit_logs` (
  `id` bigint(20) NOT NULL,
  `action_type` varchar(255) DEFAULT NULL,
  `changed_by` varchar(255) DEFAULT NULL,
  `entity_id` bigint(20) DEFAULT NULL,
  `entity_name` varchar(255) DEFAULT NULL,
  `new_data` longtext DEFAULT NULL,
  `old_data` longtext DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `audit_logs`
--

INSERT INTO `audit_logs` (`id`, `action_type`, `changed_by`, `entity_id`, `entity_name`, `new_data`, `old_data`, `timestamp`) VALUES
(1, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Admin\",\"email\":\"admin@example.com\",\"phone\":\"0123456789\",\"password\":\"$2a$10$1BqEmDJCskfuylZk/UZcd.Dv3Kln77rKuh49Kx8tEPEK.MuQ7GbAO\",\"identityCard\":null,\"driverLicense\":null,\"role\":\"ADMIN\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-12 11:52:36.000000'),
(2, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Staff\",\"email\":\"staff@example.com\",\"phone\":\"0112233445\",\"password\":\"$2a$10$1w5N9Ig2kSC0wctB/DMQFOaGebcrcL5f9I7X66j9mIfdhPVM/MY6K\",\"identityCard\":null,\"driverLicense\":null,\"role\":\"STAFF\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-12 11:52:36.000000'),
(3, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$h0uhklPnCVBNG3vW9CTBYOoLlI1wuEJWPWrEmBAMlmkpzkU7AeEve\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-12 11:52:36.000000'),
(4, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Vision\",\"brand\":\"Honda\",\"pricePerDay\":60000.0,\"description\":\"Xe ga Honda Vision đời mới\",\"licensePlate\":\"29E1-12345\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/c38f8c1c-bfa7-456b-aba6-5a1b4646a936_download.jpg\"},{\"id\":null,\"url\":\"uploads/f425535f-50e1-43a7-b009-cbd1cc72cb54_download.jpg\"}]}', NULL, '2025-04-12 12:01:28.000000'),
(5, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/c38f8c1c-bfa7-456b-aba6-5a1b4646a936_download.jpg\"}', NULL, '2025-04-12 12:01:28.000000'),
(6, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/f425535f-50e1-43a7-b009-cbd1cc72cb54_download.jpg\"}', NULL, '2025-04-12 12:01:28.000000'),
(7, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Air Blade\",\"brand\":\"Honda\",\"pricePerDay\":70000.0,\"description\":\"Xe ga Honda AirBlade 2025\",\"licensePlate\":\"29E2-12345\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/d7b0fca2-41b4-42a6-8251-6cf716e7e914_download.jpg\"},{\"id\":null,\"url\":\"uploads/26be6b6b-19f2-4f26-8499-9c6307727373_download.jpg\"},{\"id\":null,\"url\":\"uploads/fe32d598-3b33-4b84-b3bd-07e034c13da6_download.jpg\"}]}', NULL, '2025-04-12 12:02:35.000000'),
(8, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/d7b0fca2-41b4-42a6-8251-6cf716e7e914_download.jpg\"}', NULL, '2025-04-12 12:02:35.000000'),
(9, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/26be6b6b-19f2-4f26-8499-9c6307727373_download.jpg\"}', NULL, '2025-04-12 12:02:35.000000'),
(10, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/fe32d598-3b33-4b84-b3bd-07e034c13da6_download.jpg\"}', NULL, '2025-04-12 12:02:35.000000'),
(11, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Vision 2023\",\"brand\":\"Honda\",\"pricePerDay\":120000.0,\"description\":\"Xe tay ga tiết kiệm xăng, phù hợp nữ giới\",\"licensePlate\":\"29B1-12345\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/c9511bd5-b669-48bc-8297-39d37623c087_download.jpg\"},{\"id\":null,\"url\":\"uploads/624323c4-8811-456e-88ad-03c70e1156a2_download.jpg\"},{\"id\":null,\"url\":\"uploads/5463b84e-a7c0-48d6-b92f-75cd6250efb5_download.jpg\"}]}', NULL, '2025-04-12 14:54:18.000000'),
(12, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/c9511bd5-b669-48bc-8297-39d37623c087_download.jpg\"}', NULL, '2025-04-12 14:54:18.000000'),
(13, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/624323c4-8811-456e-88ad-03c70e1156a2_download.jpg\"}', NULL, '2025-04-12 14:54:18.000000'),
(14, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/5463b84e-a7c0-48d6-b92f-75cd6250efb5_download.jpg\"}', NULL, '2025-04-12 14:54:18.000000'),
(15, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Air Blade 2022\",\"brand\":\"Honda\",\"pricePerDay\":150000.0,\"description\":\"Xe tay ga thể thao, động cơ khỏe\",\"licensePlate\":\"30B2-67890\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/4855f9d5-0694-4064-9352-766ea2ef378f_download.jpg\"},{\"id\":null,\"url\":\"uploads/c174ee6c-a9ea-4392-8d58-7f1a4d6b8a85_download.jpg\"},{\"id\":null,\"url\":\"uploads/6e91ff16-0fee-44d2-a600-601b11e2e19e_download.jpg\"}]}', NULL, '2025-04-12 14:54:54.000000'),
(16, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/4855f9d5-0694-4064-9352-766ea2ef378f_download.jpg\"}', NULL, '2025-04-12 14:54:54.000000'),
(17, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/c174ee6c-a9ea-4392-8d58-7f1a4d6b8a85_download.jpg\"}', NULL, '2025-04-12 14:54:54.000000'),
(18, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/6e91ff16-0fee-44d2-a600-601b11e2e19e_download.jpg\"}', NULL, '2025-04-12 14:54:54.000000'),
(19, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Sirius RC\",\"brand\":\"Yamaha\",\"pricePerDay\":100000.0,\"description\":\"Xe số bền bỉ, thích hợp đi phượt\",\"licensePlate\":\"29Y1-23456\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/dbf65276-c4af-4e51-ac07-9dc097c47009_download.jpg\"}]}', NULL, '2025-04-12 14:55:27.000000'),
(20, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/dbf65276-c4af-4e51-ac07-9dc097c47009_download.jpg\"}', NULL, '2025-04-12 14:55:27.000000'),
(21, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Wave Alpha 110cc\",\"brand\":\"Honda\",\"pricePerDay\":90000.0,\"description\":\"Xe số đơn giản, dễ sử dụng\",\"licensePlate\":\"29L1-11223\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/18142d67-fe23-4691-a255-803009933842_download.jpg\"}]}', NULL, '2025-04-12 14:56:26.000000'),
(22, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/18142d67-fe23-4691-a255-803009933842_download.jpg\"}', NULL, '2025-04-12 14:56:26.000000'),
(23, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Janus\",\"brand\":\"\\tYamaha\",\"pricePerDay\":120000.0,\"description\":\"Xe tay ga nhẹ nhàng, thời trang\",\"licensePlate\":\"30X1-44567\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/814eb1cd-b636-4c70-a09d-6f9509684673_download.jpg\"}]}', NULL, '2025-04-12 14:56:54.000000'),
(24, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/814eb1cd-b636-4c70-a09d-6f9509684673_download.jpg\"}', NULL, '2025-04-12 14:56:54.000000'),
(25, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Lead 2021\",\"brand\":\"Honda\",\"pricePerDay\":130000.0,\"description\":\"Cốp rộng, thích hợp cho nữ đi làm\",\"licensePlate\":\"29B3-99887\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/9eecbdc6-0d97-40a2-a08b-d9098e5b7afe_download.jpg\"},{\"id\":null,\"url\":\"uploads/50e27387-4a93-46cb-b514-5de31fbc4cda_download.jpg\"}]}', NULL, '2025-04-12 14:57:24.000000'),
(26, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/9eecbdc6-0d97-40a2-a08b-d9098e5b7afe_download.jpg\"}', NULL, '2025-04-12 14:57:24.000000'),
(27, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/50e27387-4a93-46cb-b514-5de31fbc4cda_download.jpg\"}', NULL, '2025-04-12 14:57:24.000000'),
(28, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Nouvo LX\",\"brand\":\"\\tYamaha\",\"pricePerDay\":110000.0,\"description\":\"Xe tay ga cũ, động cơ mạnh mẽ\",\"licensePlate\":\"\\t30G1-88992\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/64b018f3-8212-4eec-b6a6-76101ea3d2b7_download.jpg\"}]}', NULL, '2025-04-12 14:57:49.000000'),
(29, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/64b018f3-8212-4eec-b6a6-76101ea3d2b7_download.jpg\"}', NULL, '2025-04-12 14:57:49.000000'),
(30, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Vision 2019\",\"brand\":\"Honda\",\"pricePerDay\":100000.0,\"description\":\"Mẫu cũ, tiết kiệm xăng, dễ lái\",\"licensePlate\":\"29Z1-11456\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/ee47e7df-5c63-4054-bc3c-1f71d94ef5eb_download.jpg\"},{\"id\":null,\"url\":\"uploads/ce720184-fa2e-4ebd-afbb-e33abe25b7f4_download.jpg\"}]}', NULL, '2025-04-12 14:58:28.000000'),
(31, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/ee47e7df-5c63-4054-bc3c-1f71d94ef5eb_download.jpg\"}', NULL, '2025-04-12 14:58:28.000000'),
(32, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/ce720184-fa2e-4ebd-afbb-e33abe25b7f4_download.jpg\"}', NULL, '2025-04-12 14:58:28.000000'),
(33, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Future 125 FI\",\"brand\":\"Honda\",\"pricePerDay\":110000.0,\"description\":\"\\tXe số đời mới, vận hành êm ái\",\"licensePlate\":\"29K1-33445\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/609f343e-41b2-41fd-88b2-56346bc96a90_download.jpg\"},{\"id\":null,\"url\":\"uploads/ddd3843c-b9ab-4ee7-8563-0fefe8712b83_download.jpg\"}]}', NULL, '2025-04-12 14:58:57.000000'),
(34, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/609f343e-41b2-41fd-88b2-56346bc96a90_download.jpg\"}', NULL, '2025-04-12 14:58:57.000000'),
(35, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/ddd3843c-b9ab-4ee7-8563-0fefe8712b83_download.jpg\"}', NULL, '2025-04-12 14:58:57.000000'),
(36, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Wave RSX FI\",\"brand\":\"Honda\",\"pricePerDay\":95000.0,\"description\":\"Xe số tiết kiệm xăng, bền bỉ\",\"licensePlate\":\"30B4-66112\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/40d1c5de-cf50-462c-b760-2d4ed93725d9_download.jpg\"},{\"id\":null,\"url\":\"uploads/81113d25-182a-4b5a-92bd-172c0ee32388_download.jpg\"}]}', NULL, '2025-04-12 14:59:45.000000'),
(37, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/40d1c5de-cf50-462c-b760-2d4ed93725d9_download.jpg\"}', NULL, '2025-04-12 14:59:45.000000'),
(38, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/81113d25-182a-4b5a-92bd-172c0ee32388_download.jpg\"}', NULL, '2025-04-12 14:59:45.000000'),
(39, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Grande\",\"brand\":\"Honda\",\"pricePerDay\":95000.0,\"description\":\"Xe tay ga cao cấp, cốp rộng, êm ái\",\"licensePlate\":\"30B4-66112\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/c06c9c15-0e5f-493a-83e3-5b09deea9f85_download.jpg\"},{\"id\":null,\"url\":\"uploads/fe34b111-2f50-409e-9a81-dfaf13a43b7c_download.jpg\"}]}', NULL, '2025-04-12 15:00:23.000000'),
(40, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/c06c9c15-0e5f-493a-83e3-5b09deea9f85_download.jpg\"}', NULL, '2025-04-12 15:00:23.000000'),
(41, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/fe34b111-2f50-409e-9a81-dfaf13a43b7c_download.jpg\"}', NULL, '2025-04-12 15:00:23.000000'),
(42, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"SH Mode\",\"brand\":\"Honda\",\"pricePerDay\":180000.0,\"description\":\"Xe tay ga cao cấp, thời trang và mạnh mẽ\",\"licensePlate\":\"30A1-55221\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/f03d5862-ca65-453d-8d04-3455226b957b_download.jpg\"},{\"id\":null,\"url\":\"uploads/74ba02fe-5695-411b-9b6e-1cfc8f1652e9_download.jpg\"}]}', NULL, '2025-04-12 15:00:53.000000'),
(43, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/f03d5862-ca65-453d-8d04-3455226b957b_download.jpg\"}', NULL, '2025-04-12 15:00:53.000000'),
(44, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/74ba02fe-5695-411b-9b6e-1cfc8f1652e9_download.jpg\"}', NULL, '2025-04-12 15:00:53.000000'),
(45, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Vision 2022\",\"brand\":\"Honda\",\"pricePerDay\":115000.0,\"description\":\"Xe mới, động cơ êm, phù hợp thành phố\",\"licensePlate\":\"29T1-22334\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/a6e679b4-ca99-4c45-8036-a9ed8945329a_download.jpg\"},{\"id\":null,\"url\":\"uploads/ff859d42-8b30-494e-8e64-41a53f64f8d5_download.jpg\"}]}', NULL, '2025-04-12 15:01:38.000000'),
(46, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/a6e679b4-ca99-4c45-8036-a9ed8945329a_download.jpg\"}', NULL, '2025-04-12 15:01:38.000000'),
(47, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/ff859d42-8b30-494e-8e64-41a53f64f8d5_download.jpg\"}', NULL, '2025-04-12 15:01:38.000000'),
(48, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Air Blade 125cc\",\"brand\":\"Honda\",\"pricePerDay\":140000.0,\"description\":\"Mạnh mẽ, thiết kế thể thao\",\"licensePlate\":\"30C1-66443\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/237fce77-ef4c-46e9-82e0-15a531b724ac_download.jpg\"},{\"id\":null,\"url\":\"uploads/e96199ab-6020-4228-a691-314ddd60aeca_download.jpg\"},{\"id\":null,\"url\":\"uploads/03e6a9ac-850d-4165-9c65-fbb333c8a53b_download.jpg\"},{\"id\":null,\"url\":\"uploads/cf36f60f-99fe-4212-a4fd-508e5609e7f4_download.jpg\"}]}', NULL, '2025-04-12 15:08:42.000000'),
(49, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/237fce77-ef4c-46e9-82e0-15a531b724ac_download.jpg\"}', NULL, '2025-04-12 15:08:42.000000'),
(50, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/e96199ab-6020-4228-a691-314ddd60aeca_download.jpg\"}', NULL, '2025-04-12 15:08:42.000000'),
(51, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/03e6a9ac-850d-4165-9c65-fbb333c8a53b_download.jpg\"}', NULL, '2025-04-12 15:08:42.000000'),
(52, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/cf36f60f-99fe-4212-a4fd-508e5609e7f4_download.jpg\"}', NULL, '2025-04-12 15:08:42.000000'),
(53, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Jupiter FI\",\"brand\":\"\\tYamaha\",\"pricePerDay\":105000.0,\"description\":\"Xe số mạnh mẽ, phù hợp đường dài\",\"licensePlate\":\"29D1-12398\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/b8a237e6-46e9-4240-8e6e-d1aa457c0056_download.jpg\"},{\"id\":null,\"url\":\"uploads/4ec3841b-c4ce-43c1-a94f-17824bb0f426_download.jpg\"},{\"id\":null,\"url\":\"uploads/d07255a9-7c62-4d06-83dc-43dae115563c_download.jpg\"},{\"id\":null,\"url\":\"uploads/51816b2e-c6ff-4931-9e6a-3e657ff213d2_download.jpg\"}]}', NULL, '2025-04-12 15:09:16.000000'),
(54, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/b8a237e6-46e9-4240-8e6e-d1aa457c0056_download.jpg\"}', NULL, '2025-04-12 15:09:16.000000'),
(55, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/4ec3841b-c4ce-43c1-a94f-17824bb0f426_download.jpg\"}', NULL, '2025-04-12 15:09:16.000000'),
(56, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/d07255a9-7c62-4d06-83dc-43dae115563c_download.jpg\"}', NULL, '2025-04-12 15:09:16.000000'),
(57, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/51816b2e-c6ff-4931-9e6a-3e657ff213d2_download.jpg\"}', NULL, '2025-04-12 15:09:16.000000'),
(58, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Click 125i\",\"brand\":\"Honda\",\"pricePerDay\":135000.0,\"description\":\"Tay ga Thái, kiểu dáng thể thao\",\"licensePlate\":\"30H1-98765\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/8a21e34a-6889-47a7-bffb-1c9d25e1dd9d_download.jpg\"},{\"id\":null,\"url\":\"uploads/87aeb5a3-6fa5-4a5a-b349-718d3c5bfa7c_download.jpg\"},{\"id\":null,\"url\":\"uploads/285258fa-fa33-47ba-a37c-3940643175aa_download.jpg\"},{\"id\":null,\"url\":\"uploads/32509f2f-8986-472e-a9b6-01be600a5aad_download.jpg\"}]}', NULL, '2025-04-12 15:09:51.000000'),
(59, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/8a21e34a-6889-47a7-bffb-1c9d25e1dd9d_download.jpg\"}', NULL, '2025-04-12 15:09:51.000000'),
(60, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/87aeb5a3-6fa5-4a5a-b349-718d3c5bfa7c_download.jpg\"}', NULL, '2025-04-12 15:09:51.000000'),
(61, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/285258fa-fa33-47ba-a37c-3940643175aa_download.jpg\"}', NULL, '2025-04-12 15:09:51.000000'),
(62, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/32509f2f-8986-472e-a9b6-01be600a5aad_download.jpg\"}', NULL, '2025-04-12 15:09:51.000000'),
(63, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"PCX 150cc\",\"brand\":\"Honda\",\"pricePerDay\":170000.0,\"description\":\"Tay ga phân khối lớn, chạy xa ổn định\",\"licensePlate\":\"29E1-77889\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/251325a0-5a71-45fb-8409-5af01365765d_download.jpg\"},{\"id\":null,\"url\":\"uploads/06a3a9be-54a2-45c7-8481-892cdd4c5c34_download.jpg\"},{\"id\":null,\"url\":\"uploads/9ad911af-1546-435d-8270-b6f5dda30103_download.jpg\"},{\"id\":null,\"url\":\"uploads/06491d6d-5391-441a-9d13-d34eb02d025b_download.jpg\"}]}', NULL, '2025-04-12 15:10:23.000000'),
(64, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/251325a0-5a71-45fb-8409-5af01365765d_download.jpg\"}', NULL, '2025-04-12 15:10:23.000000'),
(65, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/06a3a9be-54a2-45c7-8481-892cdd4c5c34_download.jpg\"}', NULL, '2025-04-12 15:10:23.000000'),
(66, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/9ad911af-1546-435d-8270-b6f5dda30103_download.jpg\"}', NULL, '2025-04-12 15:10:23.000000'),
(67, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/06491d6d-5391-441a-9d13-d34eb02d025b_download.jpg\"}', NULL, '2025-04-12 15:10:23.000000'),
(68, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Wave Thái 110cc\",\"brand\":\"Honda\",\"pricePerDay\":100000.0,\"description\":\"Xe số nhập khẩu, vận hành mượt mà\",\"licensePlate\":\"30Z1-33567\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/a570c802-d87c-4fa5-854b-f18bebeacbfa_download.jpg\"},{\"id\":null,\"url\":\"uploads/3e5c1806-8972-4f3b-b063-5fb8d94487f0_download.jpg\"},{\"id\":null,\"url\":\"uploads/3df6816c-9f23-4b0e-8935-0eb5f5efcdb2_download.jpg\"}]}', NULL, '2025-04-12 15:10:48.000000'),
(69, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/a570c802-d87c-4fa5-854b-f18bebeacbfa_download.jpg\"}', NULL, '2025-04-12 15:10:48.000000'),
(70, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/3e5c1806-8972-4f3b-b063-5fb8d94487f0_download.jpg\"}', NULL, '2025-04-12 15:10:48.000000'),
(71, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/3df6816c-9f23-4b0e-8935-0eb5f5efcdb2_download.jpg\"}', NULL, '2025-04-12 15:10:48.000000'),
(72, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Mio Classico\",\"brand\":\"Yamaha\",\"pricePerDay\":95000.0,\"description\":\"Tay ga nhỏ gọn, dễ điều khiển\",\"licensePlate\":\"29Y2-44551\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/2fc9a3f8-0d17-47ab-86af-6a51574f4306_download.jpg\"},{\"id\":null,\"url\":\"uploads/b4f36a0d-2cf7-40d5-8ab1-d03f8011149c_download.jpg\"},{\"id\":null,\"url\":\"uploads/46c801c6-7ba5-43dc-88a5-3ca5fc440d8f_download.jpg\"},{\"id\":null,\"url\":\"uploads/b59e18cc-dd55-415e-890d-0568d44ac0c5_download.jpg\"}]}', NULL, '2025-04-12 15:11:13.000000'),
(73, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/2fc9a3f8-0d17-47ab-86af-6a51574f4306_download.jpg\"}', NULL, '2025-04-12 15:11:13.000000'),
(74, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/b4f36a0d-2cf7-40d5-8ab1-d03f8011149c_download.jpg\"}', NULL, '2025-04-12 15:11:13.000000'),
(75, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/46c801c6-7ba5-43dc-88a5-3ca5fc440d8f_download.jpg\"}', NULL, '2025-04-12 15:11:13.000000'),
(76, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/b59e18cc-dd55-415e-890d-0568d44ac0c5_download.jpg\"}', NULL, '2025-04-12 15:11:13.000000'),
(77, 'CREATE', 'admin@example.com', NULL, 'Vehicle', '{\"id\":null,\"name\":\"Vario 150cc\",\"brand\":\"Honda\",\"pricePerDay\":160000.0,\"description\":\"Tay ga thể thao, mạnh, kiểu dáng đẹp\",\"licensePlate\":\"30M1-55667\",\"createdAt\":null,\"updatedAt\":null,\"vehicleImages\":[{\"id\":null,\"url\":\"uploads/ea00db49-89a2-45b6-8c4f-96fcd8f269a9_download.jpg\"},{\"id\":null,\"url\":\"uploads/4ed3a0ed-3aff-4af2-8caf-28ebc026b918_download.jpg\"},{\"id\":null,\"url\":\"uploads/ca74af84-62ac-45ea-b6d3-0415e3beab34_download.jpg\"}]}', NULL, '2025-04-12 15:11:45.000000'),
(78, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/ea00db49-89a2-45b6-8c4f-96fcd8f269a9_download.jpg\"}', NULL, '2025-04-12 15:11:45.000000'),
(79, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/4ed3a0ed-3aff-4af2-8caf-28ebc026b918_download.jpg\"}', NULL, '2025-04-12 15:11:45.000000'),
(80, 'CREATE', 'admin@example.com', NULL, 'VehicleImage', '{\"id\":null,\"url\":\"uploads/ca74af84-62ac-45ea-b6d3-0415e3beab34_download.jpg\"}', NULL, '2025-04-12 15:11:45.000000'),
(81, 'UPDATE', 'customer@example.com', 3, 'User', '{\"id\":3,\"fullName\":\"Phạm Nhat Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$h0uhklPnCVBNG3vW9CTBYOoLlI1wuEJWPWrEmBAMlmkpzkU7AeEve\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img-license.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-12T11:52:36\",\"updatedAt\":\"2025-04-12T11:52:36\",\"rentals\":[],\"notifications\":[]}', '{\"id\":3,\"fullName\":\"Phạm Nhat Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$h0uhklPnCVBNG3vW9CTBYOoLlI1wuEJWPWrEmBAMlmkpzkU7AeEve\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img-license.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-12T11:52:36\",\"updatedAt\":\"2025-04-12T11:52:36\",\"rentals\":[],\"notifications\":[]}', '2025-04-12 23:35:06.000000'),
(82, 'UPDATE', 'pna120104@gmail.com', 3, 'User', '{\"id\":3,\"fullName\":\"Phạm Nhat Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$h0uhklPnCVBNG3vW9CTBYOoLlI1wuEJWPWrEmBAMlmkpzkU7AeEve\",\"identityCard\":\"uploads/e41e0727-3c13-47ba-b8da-e7c585451c3f_Screenshot 2025-04-12 192146.png\",\"driverLicense\":\"uploads/img-license.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-12T11:52:36\",\"updatedAt\":\"2025-04-12T23:35:06\",\"rentals\":[],\"notifications\":[]}', '{\"id\":3,\"fullName\":\"Phạm Nhat Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$h0uhklPnCVBNG3vW9CTBYOoLlI1wuEJWPWrEmBAMlmkpzkU7AeEve\",\"identityCard\":\"uploads/e41e0727-3c13-47ba-b8da-e7c585451c3f_Screenshot 2025-04-12 192146.png\",\"driverLicense\":\"uploads/img-license.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-12T11:52:36\",\"updatedAt\":\"2025-04-12T23:35:06\",\"rentals\":[],\"notifications\":[]}', '2025-04-12 23:42:42.000000'),
(83, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$7tnJzZB9XoMLTD5uEVowgeLyoQePHxpocAxmtmYa7rIq7CZxxOKY2\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-13 00:37:23.000000'),
(84, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$x7DR.Iy7Pe6xASC7UvoJ0edRPKBth3EuEJH15IGheYZ6UIZll3f1y\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-13 00:50:55.000000'),
(85, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$xk2iyCPJiEjRWdF6rSXwd.YOA2c7a58pJ7X3Ua08M5UNazLqCgbMK\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-13 10:33:56.000000'),
(86, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$F7BbQRY5ijVURZxFZxB9pOaS.GTHXMx0BJsliImTkF2B0bQ1nSmlK\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-13 10:37:20.000000'),
(87, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$IhsxmuzTZPlghjmtgRkVi.xhYW8SykN9x1ma4Q5lCoUCnib8n1P4G\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-13 10:40:11.000000'),
(88, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$aiW90ohtLKd5Xsm3vPefpuTlQXp2oZ7k0GWcsis..yESP00fgr60K\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-13 10:57:40.000000'),
(89, 'UPDATE', 'customer@example.com', 9, 'User', '{\"id\":9,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654323\",\"password\":\"$2a$10$aiW90ohtLKd5Xsm3vPefpuTlQXp2oZ7k0GWcsis..yESP00fgr60K\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T10:57:40\",\"updatedAt\":\"2025-04-13T10:57:40\",\"rentals\":[],\"notifications\":[]}', '{\"id\":9,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654323\",\"password\":\"$2a$10$aiW90ohtLKd5Xsm3vPefpuTlQXp2oZ7k0GWcsis..yESP00fgr60K\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T10:57:40\",\"updatedAt\":\"2025-04-13T10:57:40\",\"rentals\":[],\"notifications\":[]}', '2025-04-13 11:05:20.000000'),
(90, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$1hdkWzFcQe7MU8cbAJQf4OhJM4YbvBLrKYqeQqMlqdK1MLcgCIGKK\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-13 11:28:17.000000'),
(91, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-13 14:14:48.000000'),
(92, 'UPDATE', 'customer@example.com', 11, 'User', '{\"id\":11,\"fullName\":\"Phạm Nhật Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T14:14:48\",\"updatedAt\":\"2025-04-13T14:14:48\",\"rentals\":[],\"notifications\":[]}', '{\"id\":11,\"fullName\":\"Phạm Nhật Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T14:14:48\",\"updatedAt\":\"2025-04-13T14:14:48\",\"rentals\":[],\"notifications\":[]}', '2025-04-13 15:01:25.000000'),
(93, 'CREATE', 'SYSTEM', NULL, 'User', '{\"id\":null,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-13 15:25:29.000000'),
(94, 'UPDATE', 'pna120104@gmail.com', 11, 'User', '{\"id\":11,\"fullName\":\"Phạm Nhật Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380227\",\"password\":\"$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T14:14:48\",\"updatedAt\":\"2025-04-13T15:01:25\",\"rentals\":[],\"notifications\":[]}', '{\"id\":11,\"fullName\":\"Phạm Nhật Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380227\",\"password\":\"$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T14:14:48\",\"updatedAt\":\"2025-04-13T15:01:25\",\"rentals\":[],\"notifications\":[]}', '2025-04-13 17:12:53.000000'),
(95, 'UPDATE', 'pna120104@gmail.com', 11, 'User', '{\"id\":11,\"fullName\":\"Phạm Nhật Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T14:14:48\",\"updatedAt\":\"2025-04-13T17:12:53\",\"rentals\":[],\"notifications\":[]}', '{\"id\":11,\"fullName\":\"Phạm Nhật Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T14:14:48\",\"updatedAt\":\"2025-04-13T17:12:53\",\"rentals\":[],\"notifications\":[]}', '2025-04-13 17:16:31.000000'),
(96, 'CREATE', 'anonymousUser', NULL, 'User', '{\"id\":null,\"fullName\":\"phạm Nhật Anh\",\"email\":\"ppap3537@gmail.com\",\"phone\":\"0988765439\",\"password\":\"$2a$10$z5/X0d3VcI32fm1wLSVo/.hok85Nflk2AZ9LrMzYWJKZuKIq39S.u\",\"identityCard\":\"uploads/07895792-3a30-44eb-b125-02cf013423a9_identityCard.jpg\",\"driverLicense\":\"uploads/cb87c4aa-5b93-4eaa-9dbd-e096e06d94cc_driverLicense.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-13 17:32:41.000000'),
(97, 'UPDATE', 'pna120104@gmail.com', 11, 'User', '{\"id\":11,\"fullName\":\"Phạm Nhật Anh 1\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T14:14:48\",\"updatedAt\":\"2025-04-13T17:16:31\",\"rentals\":[],\"notifications\":[]}', '{\"id\":11,\"fullName\":\"Phạm Nhật Anh 1\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW\",\"identityCard\":\"uploads/img.png\",\"driverLicense\":\"uploads/img.png\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T14:14:48\",\"updatedAt\":\"2025-04-13T17:16:31\",\"rentals\":[],\"notifications\":[]}', '2025-04-13 19:56:35.000000'),
(98, 'CREATE', 'anonymousUser', NULL, 'User', '{\"id\":null,\"fullName\":\"Phạm Phạm\",\"email\":\"pna123@gmail.com\",\"phone\":\"0987674532\",\"password\":\"$2a$10$OqiwWT1vFNFDo48oGXpFJeBEl843e9A/yO6eqeqNesrBWw0hCvevy\",\"identityCard\":\"uploads/bf376315-000a-4412-bbba-ac339a96d0d7_identityCard.jpg\",\"driverLicense\":\"uploads/40377d0d-811c-41f0-924a-38bf7d82b38d_driverLicense.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":null,\"updatedAt\":null,\"rentals\":null,\"notifications\":null}', NULL, '2025-04-13 20:04:14.000000'),
(99, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/6fb838ac-4e9c-4845-b33f-fd0683772f2e_upload_1744552674106.jpg\",\"driverLicense\":\"uploads/9be2799f-9fac-47ca-af2d-07cb502738f4_upload_1744552674131.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-13T15:25:29\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/6fb838ac-4e9c-4845-b33f-fd0683772f2e_upload_1744552674106.jpg\",\"driverLicense\":\"uploads/9be2799f-9fac-47ca-af2d-07cb502738f4_upload_1744552674131.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-13T15:25:29\",\"rentals\":[],\"notifications\":[]}', '2025-04-13 20:57:54.000000'),
(100, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/ff70dc42-c12b-4e80-ae9b-ae872ffc9c2a_upload_1744563958672.jpg\",\"driverLicense\":\"uploads/3cb20a9a-cbab-4385-9a3f-6eb41f3fb74e_upload_1744563958690.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-13T20:57:54\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/ff70dc42-c12b-4e80-ae9b-ae872ffc9c2a_upload_1744563958672.jpg\",\"driverLicense\":\"uploads/3cb20a9a-cbab-4385-9a3f-6eb41f3fb74e_upload_1744563958690.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-13T20:57:54\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 00:06:00.000000'),
(101, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/ab4f0e80-d21d-4dfd-8297-a805bc53250b_upload_1744563969385.jpg\",\"driverLicense\":\"uploads/11e4560d-55f2-4d32-9868-5db979f490b9_upload_1744563969399.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:06:00\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/ab4f0e80-d21d-4dfd-8297-a805bc53250b_upload_1744563969385.jpg\",\"driverLicense\":\"uploads/11e4560d-55f2-4d32-9868-5db979f490b9_upload_1744563969399.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:06:00\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 00:06:10.000000'),
(102, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/58c3be9b-4e7e-484a-8933-aaef9daf9956_upload_1744563985904.jpg\",\"driverLicense\":\"uploads/251a8b9f-d5ba-47a2-8fec-574699c6fccf_upload_1744563985986.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:06:10\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/58c3be9b-4e7e-484a-8933-aaef9daf9956_upload_1744563985904.jpg\",\"driverLicense\":\"uploads/251a8b9f-d5ba-47a2-8fec-574699c6fccf_upload_1744563985986.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:06:10\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 00:06:27.000000'),
(103, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/3aca30c1-9da3-492a-8e47-2cf694f02877_upload_1744563998434.jpg\",\"driverLicense\":\"uploads/c57f6be2-47ae-4fdf-8e18-7a487d01d5de_upload_1744563998449.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:06:27\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/3aca30c1-9da3-492a-8e47-2cf694f02877_upload_1744563998434.jpg\",\"driverLicense\":\"uploads/c57f6be2-47ae-4fdf-8e18-7a487d01d5de_upload_1744563998449.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:06:27\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 00:06:39.000000'),
(104, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/f5e1895b-7384-4157-94c8-2f2db073ca32_upload_1744565924620.jpg\",\"driverLicense\":\"uploads/401cd1b0-75ac-4aff-8c91-27a347de1bd4_upload_1744565924640.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:06:39\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/f5e1895b-7384-4157-94c8-2f2db073ca32_upload_1744565924620.jpg\",\"driverLicense\":\"uploads/401cd1b0-75ac-4aff-8c91-27a347de1bd4_upload_1744565924640.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:06:39\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 00:38:45.000000'),
(105, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/402c83eb-b3e3-4925-8ce4-efaaa4169428_upload_1744565966556.jpg\",\"driverLicense\":\"uploads/09ba3640-9f62-4f8d-baf2-1576e9ac33e6_upload_1744565966567.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:38:45\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/402c83eb-b3e3-4925-8ce4-efaaa4169428_upload_1744565966556.jpg\",\"driverLicense\":\"uploads/09ba3640-9f62-4f8d-baf2-1576e9ac33e6_upload_1744565966567.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:38:45\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 00:39:27.000000'),
(106, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/f2a4c8b6-b310-43a9-a3b6-ab96b3989172_upload_1744565972403.jpg\",\"driverLicense\":\"uploads/7a4836a2-39a8-4418-a1a7-4161d4210f02_upload_1744565972419.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:39:27\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer\",\"email\":\"customer@example.com\",\"phone\":\"0987654321\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/f2a4c8b6-b310-43a9-a3b6-ab96b3989172_upload_1744565972403.jpg\",\"driverLicense\":\"uploads/7a4836a2-39a8-4418-a1a7-4161d4210f02_upload_1744565972419.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:39:27\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 00:39:33.000000'),
(107, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer12\",\"email\":\"customer@example.com\",\"phone\":\"0987654339\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/eed2ddec-647e-46df-a5ea-ccf2977f94b8_upload_1744566932745.jpg\",\"driverLicense\":\"uploads/83572b7c-d7fd-44c7-a491-364136accfbc_upload_1744566932766.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:39:33\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer12\",\"email\":\"customer@example.com\",\"phone\":\"0987654339\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/eed2ddec-647e-46df-a5ea-ccf2977f94b8_upload_1744566932745.jpg\",\"driverLicense\":\"uploads/83572b7c-d7fd-44c7-a491-364136accfbc_upload_1744566932766.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:39:33\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 00:55:34.000000'),
(108, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer12\",\"email\":\"customer@example.com\",\"phone\":\"0987654339\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/91c45819-7406-4143-ab4d-521c5351e0b8_identity_card.jpg\",\"driverLicense\":\"uploads/d6d66a4d-751b-4d2c-858d-665ab61660fe_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:55:34\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer12\",\"email\":\"customer@example.com\",\"phone\":\"0987654339\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/91c45819-7406-4143-ab4d-521c5351e0b8_identity_card.jpg\",\"driverLicense\":\"uploads/d6d66a4d-751b-4d2c-858d-665ab61660fe_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T00:55:34\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 01:09:42.000000'),
(109, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer12\",\"email\":\"customer@example.com\",\"phone\":\"0987654339\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/61697ffa-7cac-4de6-9d49-9d3c8cdff2ca_identity_card.jpg\",\"driverLicense\":\"uploads/fc1b9744-8c33-4059-9215-ea96b230b9ed_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T01:09:42\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer12\",\"email\":\"customer@example.com\",\"phone\":\"0987654339\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/61697ffa-7cac-4de6-9d49-9d3c8cdff2ca_identity_card.jpg\",\"driverLicense\":\"uploads/fc1b9744-8c33-4059-9215-ea96b230b9ed_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T01:09:42\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 01:14:25.000000'),
(110, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer12\",\"email\":\"customer@example.com\",\"phone\":\"0987654338\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/4e5a751c-c5d7-468d-a6c6-b0f7c3be3959_identity_card.jpg\",\"driverLicense\":\"uploads/fc1b9744-8c33-4059-9215-ea96b230b9ed_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T01:14:25\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer12\",\"email\":\"customer@example.com\",\"phone\":\"0987654338\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/4e5a751c-c5d7-468d-a6c6-b0f7c3be3959_identity_card.jpg\",\"driverLicense\":\"uploads/fc1b9744-8c33-4059-9215-ea96b230b9ed_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T01:14:25\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 01:16:26.000000'),
(111, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer123\",\"email\":\"customer@example.com\",\"phone\":\"0987654338\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/4e5a751c-c5d7-468d-a6c6-b0f7c3be3959_identity_card.jpg\",\"driverLicense\":\"uploads/fc1b9744-8c33-4059-9215-ea96b230b9ed_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T01:16:26\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer123\",\"email\":\"customer@example.com\",\"phone\":\"0987654338\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/4e5a751c-c5d7-468d-a6c6-b0f7c3be3959_identity_card.jpg\",\"driverLicense\":\"uploads/fc1b9744-8c33-4059-9215-ea96b230b9ed_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T01:16:26\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 01:17:59.000000'),
(112, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer123\",\"email\":\"customer@example.com\",\"phone\":\"0987654338\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/9c08e8d2-961b-420c-ad7b-47c85f5aeab1_identity_card.jpg\",\"driverLicense\":\"uploads/fc1b9744-8c33-4059-9215-ea96b230b9ed_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T01:17:59\",\"rentals\":[],\"notifications\":[]}', '{\"id\":12,\"fullName\":\"Customer123\",\"email\":\"customer@example.com\",\"phone\":\"0987654338\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/9c08e8d2-961b-420c-ad7b-47c85f5aeab1_identity_card.jpg\",\"driverLicense\":\"uploads/fc1b9744-8c33-4059-9215-ea96b230b9ed_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T01:17:59\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 01:18:25.000000'),
(113, 'UPDATE', 'admin@example.com', 20, 'Vehicle', '{\"id\":20,\"name\":\"Wave Thái 110ccc\",\"brand\":\"Honda\",\"pricePerDay\":100000.0,\"description\":\"Xe số nhập khẩu, vận hành mượt mà\",\"licensePlate\":\"30Z1-33567\",\"createdAt\":\"2025-04-12T15:10:48\",\"updatedAt\":\"2025-04-12T15:10:48\",\"vehicleImages\":[{\"id\":46,\"url\":\"uploads/a570c802-d87c-4fa5-854b-f18bebeacbfa_download.jpg\"},{\"id\":47,\"url\":\"uploads/3e5c1806-8972-4f3b-b063-5fb8d94487f0_download.jpg\"},{\"id\":48,\"url\":\"uploads/3df6816c-9f23-4b0e-8935-0eb5f5efcdb2_download.jpg\"}]}', '{\"id\":20,\"name\":\"Wave Thái 110ccc\",\"brand\":\"Honda\",\"pricePerDay\":100000.0,\"description\":\"Xe số nhập khẩu, vận hành mượt mà\",\"licensePlate\":\"30Z1-33567\",\"createdAt\":\"2025-04-12T15:10:48\",\"updatedAt\":\"2025-04-12T15:10:48\",\"vehicleImages\":[{\"id\":46,\"url\":\"uploads/a570c802-d87c-4fa5-854b-f18bebeacbfa_download.jpg\"},{\"id\":47,\"url\":\"uploads/3e5c1806-8972-4f3b-b063-5fb8d94487f0_download.jpg\"},{\"id\":48,\"url\":\"uploads/3df6816c-9f23-4b0e-8935-0eb5f5efcdb2_download.jpg\"}]}', '2025-04-14 12:55:40.000000');
INSERT INTO `audit_logs` (`id`, `action_type`, `changed_by`, `entity_id`, `entity_name`, `new_data`, `old_data`, `timestamp`) VALUES
(114, 'CREATE', 'customer@example.com', NULL, 'Rental', '{\"id\":null,\"totalPrice\":570000.0,\"amountPaid\":570000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-18T00:00:00\",\"status\":\"UNPAID\",\"paymentMethod\":\"VNPAY\",\"createdAt\":null,\"updatedAt\":null,\"rentalDetails\":[{\"id\":null,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0},{\"id\":null,\"vehicleName\":\"Vision 2023\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B1-12345\",\"pricePerDay\":120000.0}]}', NULL, '2025-04-14 13:09:06.000000'),
(115, 'CREATE', 'customer@example.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0}', NULL, '2025-04-14 13:09:06.000000'),
(116, 'CREATE', 'customer@example.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Vision 2023\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B1-12345\",\"pricePerDay\":120000.0}', NULL, '2025-04-14 13:09:06.000000'),
(117, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer123\",\"email\":\"customer@example.com\",\"phone\":\"0987654338\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/c9d2907b-da38-436c-afda-1c6d072f8dd0_identity_card.jpg\",\"driverLicense\":\"uploads/3445f7ed-7394-49cd-82ce-cb8e2a5a8a67_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T01:18:25\",\"rentals\":[{\"id\":1,\"totalPrice\":570000.0,\"amountPaid\":570000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-18T00:00:00\",\"status\":\"UNPAID\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T13:09:06\",\"updatedAt\":\"2025-04-14T13:09:06\",\"rentalDetails\":[{\"id\":1,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0},{\"id\":2,\"vehicleName\":\"Vision 2023\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B1-12345\",\"pricePerDay\":120000.0}]}],\"notifications\":[{\"id\":5,\"message\":\"Thuê xe thành công\",\"createdAt\":\"2025-04-13T02:39:58\"},{\"id\":6,\"message\":\"Sắp đến hạn trả xe\",\"createdAt\":\"2025-04-14T02:39:58\"}]}', '{\"id\":12,\"fullName\":\"Customer123\",\"email\":\"customer@example.com\",\"phone\":\"0987654338\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/c9d2907b-da38-436c-afda-1c6d072f8dd0_identity_card.jpg\",\"driverLicense\":\"uploads/3445f7ed-7394-49cd-82ce-cb8e2a5a8a67_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T01:18:25\",\"rentals\":[{\"id\":1,\"totalPrice\":570000.0,\"amountPaid\":570000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-18T00:00:00\",\"status\":\"UNPAID\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T13:09:06\",\"updatedAt\":\"2025-04-14T13:09:06\",\"rentalDetails\":[{\"id\":1,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0},{\"id\":2,\"vehicleName\":\"Vision 2023\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B1-12345\",\"pricePerDay\":120000.0}]}],\"notifications\":[{\"id\":5,\"message\":\"Thuê xe thành công\",\"createdAt\":\"2025-04-13T02:39:58\"},{\"id\":6,\"message\":\"Sắp đến hạn trả xe\",\"createdAt\":\"2025-04-14T02:39:58\"}]}', '2025-04-14 13:10:51.000000'),
(118, 'CREATE', 'customer@example.com', NULL, 'Rental', '{\"id\":null,\"totalPrice\":130000.0,\"amountPaid\":130000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-16T00:00:00\",\"status\":\"UNPAID\",\"paymentMethod\":\"VNPAY\",\"createdAt\":null,\"updatedAt\":null,\"rentalDetails\":[{\"id\":null,\"vehicleName\":\"Vision\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E1-12345\",\"pricePerDay\":60000.0},{\"id\":null,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0}]}', NULL, '2025-04-14 15:47:37.000000'),
(119, 'CREATE', 'customer@example.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Vision\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E1-12345\",\"pricePerDay\":60000.0}', NULL, '2025-04-14 15:47:37.000000'),
(120, 'CREATE', 'customer@example.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0}', NULL, '2025-04-14 15:47:37.000000'),
(121, 'CREATE', 'customer@example.com', NULL, 'Rental', '{\"id\":null,\"totalPrice\":190000.0,\"amountPaid\":190000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-16T00:00:00\",\"status\":\"UNPAID\",\"paymentMethod\":\"VNPAY\",\"createdAt\":null,\"updatedAt\":null,\"rentalDetails\":[{\"id\":null,\"vehicleName\":\"Sirius RC\",\"vehicleBrand\":\"Yamaha\",\"licensePlate\":\"29Y1-23456\",\"pricePerDay\":100000.0},{\"id\":null,\"vehicleName\":\"Wave Alpha 110cc\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29L1-11223\",\"pricePerDay\":90000.0}]}', NULL, '2025-04-14 15:50:12.000000'),
(122, 'CREATE', 'customer@example.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Sirius RC\",\"vehicleBrand\":\"Yamaha\",\"licensePlate\":\"29Y1-23456\",\"pricePerDay\":100000.0}', NULL, '2025-04-14 15:50:12.000000'),
(123, 'CREATE', 'customer@example.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Wave Alpha 110cc\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29L1-11223\",\"pricePerDay\":90000.0}', NULL, '2025-04-14 15:50:12.000000'),
(124, 'CREATE', 'customer@example.com', NULL, 'Rental', '{\"id\":null,\"totalPrice\":800000.0,\"amountPaid\":800000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-17T00:00:00\",\"status\":\"UNPAID\",\"paymentMethod\":\"VNPAY\",\"createdAt\":null,\"updatedAt\":null,\"rentalDetails\":[{\"id\":null,\"vehicleName\":\"Air Blade 2022\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B2-67890\",\"pricePerDay\":150000.0},{\"id\":null,\"vehicleName\":\"Janus\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"30X1-44567\",\"pricePerDay\":120000.0},{\"id\":null,\"vehicleName\":\"Lead 2021\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B3-99887\",\"pricePerDay\":130000.0}]}', NULL, '2025-04-14 15:55:47.000000'),
(125, 'CREATE', 'customer@example.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Air Blade 2022\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B2-67890\",\"pricePerDay\":150000.0}', NULL, '2025-04-14 15:55:47.000000'),
(126, 'CREATE', 'customer@example.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Janus\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"30X1-44567\",\"pricePerDay\":120000.0}', NULL, '2025-04-14 15:55:47.000000'),
(127, 'CREATE', 'customer@example.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Lead 2021\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B3-99887\",\"pricePerDay\":130000.0}', NULL, '2025-04-14 15:55:47.000000'),
(128, 'UPDATE', 'anonymousUser', 4, 'Rental', '{\"id\":4,\"totalPrice\":800000.0,\"amountPaid\":800000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-17T00:00:00\",\"status\":\"PENDING\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T15:55:47\",\"updatedAt\":\"2025-04-14T15:55:47\",\"rentalDetails\":[{\"id\":7,\"vehicleName\":\"Air Blade 2022\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B2-67890\",\"pricePerDay\":150000.0},{\"id\":8,\"vehicleName\":\"Janus\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"30X1-44567\",\"pricePerDay\":120000.0},{\"id\":9,\"vehicleName\":\"Lead 2021\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B3-99887\",\"pricePerDay\":130000.0}]}', '{\"id\":4,\"totalPrice\":800000.0,\"amountPaid\":800000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-17T00:00:00\",\"status\":\"PENDING\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T15:55:47\",\"updatedAt\":\"2025-04-14T15:55:47\",\"rentalDetails\":[{\"id\":7,\"vehicleName\":\"Air Blade 2022\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B2-67890\",\"pricePerDay\":150000.0},{\"id\":8,\"vehicleName\":\"Janus\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"30X1-44567\",\"pricePerDay\":120000.0},{\"id\":9,\"vehicleName\":\"Lead 2021\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B3-99887\",\"pricePerDay\":130000.0}]}', '2025-04-14 15:56:41.000000'),
(129, 'UPDATE', 'admin@example.com', 4, 'Rental', '{\"id\":4,\"totalPrice\":800000.0,\"amountPaid\":400000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-17T00:00:00\",\"status\":\"RENTED\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T15:55:47\",\"updatedAt\":\"2025-04-14T15:56:41\",\"rentalDetails\":[{\"id\":7,\"vehicleName\":\"Air Blade 2022\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B2-67890\",\"pricePerDay\":150000.0},{\"id\":8,\"vehicleName\":\"Janus\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"30X1-44567\",\"pricePerDay\":120000.0},{\"id\":9,\"vehicleName\":\"Lead 2021\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B3-99887\",\"pricePerDay\":130000.0}]}', '{\"id\":4,\"totalPrice\":800000.0,\"amountPaid\":400000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-17T00:00:00\",\"status\":\"RENTED\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T15:55:47\",\"updatedAt\":\"2025-04-14T15:56:41\",\"rentalDetails\":[{\"id\":7,\"vehicleName\":\"Air Blade 2022\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B2-67890\",\"pricePerDay\":150000.0},{\"id\":8,\"vehicleName\":\"Janus\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"30X1-44567\",\"pricePerDay\":120000.0},{\"id\":9,\"vehicleName\":\"Lead 2021\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B3-99887\",\"pricePerDay\":130000.0}]}', '2025-04-14 16:37:17.000000'),
(130, 'UPDATE', 'customer@example.com', 12, 'User', '{\"id\":12,\"fullName\":\"Customer123\",\"email\":\"customer@example.com\",\"phone\":\"0987654339\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/c9d2907b-da38-436c-afda-1c6d072f8dd0_identity_card.jpg\",\"driverLicense\":\"uploads/3445f7ed-7394-49cd-82ce-cb8e2a5a8a67_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T13:10:51\",\"rentals\":[{\"id\":4,\"totalPrice\":800000.0,\"amountPaid\":400000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-17T00:00:00\",\"status\":\"RENTED\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T15:55:47\",\"updatedAt\":\"2025-04-14T16:37:17\",\"rentalDetails\":[{\"id\":7,\"vehicleName\":\"Air Blade 2022\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B2-67890\",\"pricePerDay\":150000.0},{\"id\":8,\"vehicleName\":\"Janus\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"30X1-44567\",\"pricePerDay\":120000.0},{\"id\":9,\"vehicleName\":\"Lead 2021\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B3-99887\",\"pricePerDay\":130000.0}]}],\"notifications\":[{\"id\":5,\"message\":\"Thuê xe thành công\",\"createdAt\":\"2025-04-13T02:39:58\"},{\"id\":6,\"message\":\"Sắp đến hạn trả xe\",\"createdAt\":\"2025-04-14T02:39:58\"},{\"id\":7,\"message\":\"Đơn thuê #4 thanh toán thành công. Vui lòng đến cửa hàng nhận xe đúng thời gian đã đặt để bắt đầu hành trình nhé!\",\"createdAt\":\"2025-04-14T15:56:41\"},{\"id\":8,\"message\":\"Đơn thuê #4 đã được xác nhận và đang trong thời gian thuê. Chúc bạn có trải nghiệm tốt!\",\"createdAt\":\"2025-04-14T16:37:17\"}]}', '{\"id\":12,\"fullName\":\"Customer123\",\"email\":\"customer@example.com\",\"phone\":\"0987654339\",\"password\":\"$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC\",\"identityCard\":\"uploads/c9d2907b-da38-436c-afda-1c6d072f8dd0_identity_card.jpg\",\"driverLicense\":\"uploads/3445f7ed-7394-49cd-82ce-cb8e2a5a8a67_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T15:25:29\",\"updatedAt\":\"2025-04-14T13:10:51\",\"rentals\":[{\"id\":4,\"totalPrice\":800000.0,\"amountPaid\":400000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-17T00:00:00\",\"status\":\"RENTED\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T15:55:47\",\"updatedAt\":\"2025-04-14T16:37:17\",\"rentalDetails\":[{\"id\":7,\"vehicleName\":\"Air Blade 2022\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B2-67890\",\"pricePerDay\":150000.0},{\"id\":8,\"vehicleName\":\"Janus\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"30X1-44567\",\"pricePerDay\":120000.0},{\"id\":9,\"vehicleName\":\"Lead 2021\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B3-99887\",\"pricePerDay\":130000.0}]}],\"notifications\":[{\"id\":5,\"message\":\"Thuê xe thành công\",\"createdAt\":\"2025-04-13T02:39:58\"},{\"id\":6,\"message\":\"Sắp đến hạn trả xe\",\"createdAt\":\"2025-04-14T02:39:58\"},{\"id\":7,\"message\":\"Đơn thuê #4 thanh toán thành công. Vui lòng đến cửa hàng nhận xe đúng thời gian đã đặt để bắt đầu hành trình nhé!\",\"createdAt\":\"2025-04-14T15:56:41\"},{\"id\":8,\"message\":\"Đơn thuê #4 đã được xác nhận và đang trong thời gian thuê. Chúc bạn có trải nghiệm tốt!\",\"createdAt\":\"2025-04-14T16:37:17\"}]}', '2025-04-14 17:35:47.000000'),
(131, 'UPDATE', 'pna120104@gmail.com', 11, 'User', '{\"id\":11,\"fullName\":\"Phạm Nhật Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW\",\"identityCard\":\"uploads/3101b3ed-13ff-4274-a3bc-e93d286db3d1_identity_card.jpg\",\"driverLicense\":\"uploads/291cf636-4fee-4a8f-afee-d30f38d0930f_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T14:14:48\",\"updatedAt\":\"2025-04-13T19:56:35\",\"rentals\":[],\"notifications\":[]}', '{\"id\":11,\"fullName\":\"Phạm Nhật Anh\",\"email\":\"pna120104@gmail.com\",\"phone\":\"0865380226\",\"password\":\"$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW\",\"identityCard\":\"uploads/3101b3ed-13ff-4274-a3bc-e93d286db3d1_identity_card.jpg\",\"driverLicense\":\"uploads/291cf636-4fee-4a8f-afee-d30f38d0930f_driver_license.jpg\",\"role\":\"CUSTOMER\",\"createdAt\":\"2025-04-13T14:14:48\",\"updatedAt\":\"2025-04-13T19:56:35\",\"rentals\":[],\"notifications\":[]}', '2025-04-14 18:02:53.000000'),
(132, 'CREATE', 'pna120104@gmail.com', NULL, 'Rental', '{\"id\":null,\"totalPrice\":5250000.0,\"amountPaid\":2625000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-30T00:00:00\",\"status\":\"UNPAID\",\"paymentMethod\":\"VNPAY\",\"createdAt\":null,\"updatedAt\":null,\"rentalDetails\":[{\"id\":null,\"vehicleName\":\"Vision\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E1-12345\",\"pricePerDay\":60000.0},{\"id\":null,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0},{\"id\":null,\"vehicleName\":\"Vision 2023\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B1-12345\",\"pricePerDay\":120000.0},{\"id\":null,\"vehicleName\":\"Sirius RC\",\"vehicleBrand\":\"Yamaha\",\"licensePlate\":\"29Y1-23456\",\"pricePerDay\":100000.0}]}', NULL, '2025-04-14 18:03:23.000000'),
(133, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Vision\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E1-12345\",\"pricePerDay\":60000.0}', NULL, '2025-04-14 18:03:23.000000'),
(134, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0}', NULL, '2025-04-14 18:03:23.000000'),
(135, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Vision 2023\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B1-12345\",\"pricePerDay\":120000.0}', NULL, '2025-04-14 18:03:23.000000'),
(136, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Sirius RC\",\"vehicleBrand\":\"Yamaha\",\"licensePlate\":\"29Y1-23456\",\"pricePerDay\":100000.0}', NULL, '2025-04-14 18:03:23.000000'),
(137, 'UPDATE', 'anonymousUser', 5, 'Rental', '{\"id\":5,\"totalPrice\":5250000.0,\"amountPaid\":2625000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-30T00:00:00\",\"status\":\"PENDING\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T18:03:23\",\"updatedAt\":\"2025-04-14T18:03:23\",\"rentalDetails\":[{\"id\":10,\"vehicleName\":\"Vision\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E1-12345\",\"pricePerDay\":60000.0},{\"id\":11,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0},{\"id\":12,\"vehicleName\":\"Vision 2023\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B1-12345\",\"pricePerDay\":120000.0},{\"id\":13,\"vehicleName\":\"Sirius RC\",\"vehicleBrand\":\"Yamaha\",\"licensePlate\":\"29Y1-23456\",\"pricePerDay\":100000.0}]}', '{\"id\":5,\"totalPrice\":5250000.0,\"amountPaid\":2625000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-30T00:00:00\",\"status\":\"PENDING\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T18:03:23\",\"updatedAt\":\"2025-04-14T18:03:23\",\"rentalDetails\":[{\"id\":10,\"vehicleName\":\"Vision\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E1-12345\",\"pricePerDay\":60000.0},{\"id\":11,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0},{\"id\":12,\"vehicleName\":\"Vision 2023\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B1-12345\",\"pricePerDay\":120000.0},{\"id\":13,\"vehicleName\":\"Sirius RC\",\"vehicleBrand\":\"Yamaha\",\"licensePlate\":\"29Y1-23456\",\"pricePerDay\":100000.0}]}', '2025-04-14 18:04:39.000000'),
(138, 'CREATE', 'pna120104@gmail.com', NULL, 'Rental', '{\"id\":null,\"totalPrice\":840000.0,\"amountPaid\":840000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-15T00:00:00\",\"status\":\"UNPAID\",\"paymentMethod\":\"VNPAY\",\"createdAt\":null,\"updatedAt\":null,\"rentalDetails\":[{\"id\":null,\"vehicleName\":\"Vision\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E1-12345\",\"pricePerDay\":60000.0},{\"id\":null,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0},{\"id\":null,\"vehicleName\":\"Vision 2023\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B1-12345\",\"pricePerDay\":120000.0},{\"id\":null,\"vehicleName\":\"Air Blade 2022\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B2-67890\",\"pricePerDay\":150000.0},{\"id\":null,\"vehicleName\":\"Sirius RC\",\"vehicleBrand\":\"Yamaha\",\"licensePlate\":\"29Y1-23456\",\"pricePerDay\":100000.0},{\"id\":null,\"vehicleName\":\"Wave Alpha 110cc\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29L1-11223\",\"pricePerDay\":90000.0},{\"id\":null,\"vehicleName\":\"Janus\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"30X1-44567\",\"pricePerDay\":120000.0},{\"id\":null,\"vehicleName\":\"Lead 2021\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B3-99887\",\"pricePerDay\":130000.0}]}', NULL, '2025-04-14 18:08:30.000000'),
(139, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Vision\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E1-12345\",\"pricePerDay\":60000.0}', NULL, '2025-04-14 18:08:30.000000'),
(140, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Air Blade\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29E2-12345\",\"pricePerDay\":70000.0}', NULL, '2025-04-14 18:08:30.000000'),
(141, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Vision 2023\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B1-12345\",\"pricePerDay\":120000.0}', NULL, '2025-04-14 18:08:30.000000'),
(142, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Air Blade 2022\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B2-67890\",\"pricePerDay\":150000.0}', NULL, '2025-04-14 18:08:30.000000'),
(143, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Sirius RC\",\"vehicleBrand\":\"Yamaha\",\"licensePlate\":\"29Y1-23456\",\"pricePerDay\":100000.0}', NULL, '2025-04-14 18:08:30.000000'),
(144, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Wave Alpha 110cc\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29L1-11223\",\"pricePerDay\":90000.0}', NULL, '2025-04-14 18:08:30.000000'),
(145, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Janus\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"30X1-44567\",\"pricePerDay\":120000.0}', NULL, '2025-04-14 18:08:30.000000'),
(146, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Lead 2021\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29B3-99887\",\"pricePerDay\":130000.0}', NULL, '2025-04-14 18:08:30.000000'),
(147, 'CREATE', 'pna120104@gmail.com', NULL, 'Rental', '{\"id\":null,\"totalPrice\":305000.0,\"amountPaid\":305000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-16T00:00:00\",\"status\":\"UNPAID\",\"paymentMethod\":\"VNPAY\",\"createdAt\":null,\"updatedAt\":null,\"rentalDetails\":[{\"id\":null,\"vehicleName\":\"Nouvo LX\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"\\t30G1-88992\",\"pricePerDay\":110000.0},{\"id\":null,\"vehicleName\":\"Vision 2019\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29Z1-11456\",\"pricePerDay\":100000.0},{\"id\":null,\"vehicleName\":\"Wave RSX FI\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B4-66112\",\"pricePerDay\":95000.0}]}', NULL, '2025-04-14 20:34:24.000000'),
(148, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Nouvo LX\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"\\t30G1-88992\",\"pricePerDay\":110000.0}', NULL, '2025-04-14 20:34:24.000000'),
(149, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Vision 2019\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29Z1-11456\",\"pricePerDay\":100000.0}', NULL, '2025-04-14 20:34:24.000000'),
(150, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Wave RSX FI\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B4-66112\",\"pricePerDay\":95000.0}', NULL, '2025-04-14 20:34:24.000000'),
(151, 'UPDATE', 'anonymousUser', 7, 'Rental', '{\"id\":7,\"totalPrice\":305000.0,\"amountPaid\":305000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-16T00:00:00\",\"status\":\"PENDING\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T20:34:24\",\"updatedAt\":\"2025-04-14T20:34:24\",\"rentalDetails\":[{\"id\":22,\"vehicleName\":\"Nouvo LX\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"\\t30G1-88992\",\"pricePerDay\":110000.0},{\"id\":23,\"vehicleName\":\"Vision 2019\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29Z1-11456\",\"pricePerDay\":100000.0},{\"id\":24,\"vehicleName\":\"Wave RSX FI\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B4-66112\",\"pricePerDay\":95000.0}]}', '{\"id\":7,\"totalPrice\":305000.0,\"amountPaid\":305000.0,\"startTime\":\"2025-04-15T00:00:00\",\"endTime\":\"2025-04-16T00:00:00\",\"status\":\"PENDING\",\"paymentMethod\":\"VNPAY\",\"createdAt\":\"2025-04-14T20:34:24\",\"updatedAt\":\"2025-04-14T20:34:24\",\"rentalDetails\":[{\"id\":22,\"vehicleName\":\"Nouvo LX\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"\\t30G1-88992\",\"pricePerDay\":110000.0},{\"id\":23,\"vehicleName\":\"Vision 2019\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29Z1-11456\",\"pricePerDay\":100000.0},{\"id\":24,\"vehicleName\":\"Wave RSX FI\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"30B4-66112\",\"pricePerDay\":95000.0}]}', '2025-04-14 20:36:28.000000'),
(152, 'CREATE', 'pna120104@gmail.com', NULL, 'Rental', '{\"id\":null,\"totalPrice\":210000.0,\"amountPaid\":210000.0,\"startTime\":\"2025-04-16T00:00:00\",\"endTime\":\"2025-04-16T00:00:00\",\"status\":\"UNPAID\",\"paymentMethod\":\"VNPAY\",\"createdAt\":null,\"updatedAt\":null,\"rentalDetails\":[{\"id\":null,\"vehicleName\":\"Nouvo LX\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"\\t30G1-88992\",\"pricePerDay\":110000.0},{\"id\":null,\"vehicleName\":\"Vision 2019\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29Z1-11456\",\"pricePerDay\":100000.0}]}', NULL, '2025-04-14 21:25:14.000000'),
(153, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Nouvo LX\",\"vehicleBrand\":\"\\tYamaha\",\"licensePlate\":\"\\t30G1-88992\",\"pricePerDay\":110000.0}', NULL, '2025-04-14 21:25:14.000000'),
(154, 'CREATE', 'pna120104@gmail.com', NULL, 'RentalDetail', '{\"id\":null,\"vehicleName\":\"Vision 2019\",\"vehicleBrand\":\"Honda\",\"licensePlate\":\"29Z1-11456\",\"pricePerDay\":100000.0}', NULL, '2025-04-14 21:25:14.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notifications`
--

CREATE TABLE `notifications` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `notifications`
--

INSERT INTO `notifications` (`id`, `created_at`, `message`, `user_id`) VALUES
(3, '2025-04-13 02:39:58.000000', 'Thuê xe thành công', 13),
(4, '2025-04-14 02:39:58.000000', 'Xe sắp đến hạn trả', 13),
(5, '2025-04-13 02:39:58.000000', 'Thuê xe thành công', 12),
(6, '2025-04-14 02:39:58.000000', 'Sắp đến hạn trả xe', 12),
(7, '2025-04-14 15:56:41.000000', 'Đơn thuê #4 thanh toán thành công. Vui lòng đến cửa hàng nhận xe đúng thời gian đã đặt để bắt đầu hành trình nhé!', 12),
(8, '2025-04-14 16:37:17.000000', 'Đơn thuê #4 đã được xác nhận và đang trong thời gian thuê. Chúc bạn có trải nghiệm tốt!', 12),
(9, '2025-04-14 18:04:39.000000', 'Đơn thuê #5 thanh toán thành công. Vui lòng đến cửa hàng nhận xe đúng thời gian đã đặt để bắt đầu hành trình nhé!', 11),
(10, '2025-04-14 20:36:28.000000', 'Đơn thuê #7 thanh toán thành công. Vui lòng đến cửa hàng nhận xe đúng thời gian đã đặt để bắt đầu hành trình nhé!', 11);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rentals`
--

CREATE TABLE `rentals` (
  `id` bigint(20) NOT NULL,
  `amount_paid` double NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `status` enum('CANCELLED','COMPLETED','PENDING','RENTED','UNPAID') DEFAULT NULL,
  `total_price` double NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `rentals`
--

INSERT INTO `rentals` (`id`, `amount_paid`, `created_at`, `end_time`, `payment_method`, `start_time`, `status`, `total_price`, `updated_at`, `user_id`) VALUES
(4, 400000, '2025-04-14 15:55:47.000000', '2025-04-17 00:00:00.000000', 'VNPAY', '2025-04-15 00:00:00.000000', 'RENTED', 800000, '2025-04-14 16:37:17.000000', 12),
(5, 2625000, '2025-04-14 18:03:23.000000', '2025-04-30 00:00:00.000000', 'VNPAY', '2025-04-15 00:00:00.000000', 'PENDING', 5250000, '2025-04-14 18:04:39.000000', 11),
(7, 305000, '2025-04-14 20:34:24.000000', '2025-04-16 00:00:00.000000', 'VNPAY', '2025-04-15 00:00:00.000000', 'PENDING', 305000, '2025-04-14 20:36:28.000000', 11),
(8, 210000, '2025-04-14 21:25:14.000000', '2025-04-16 00:00:00.000000', 'VNPAY', '2025-04-16 00:00:00.000000', 'UNPAID', 210000, '2025-04-14 21:25:14.000000', 11);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `rental_details`
--

CREATE TABLE `rental_details` (
  `id` bigint(20) NOT NULL,
  `license_plate` varchar(255) DEFAULT NULL,
  `price_per_day` double DEFAULT NULL,
  `vehicle_brand` varchar(255) DEFAULT NULL,
  `vehicle_name` varchar(255) DEFAULT NULL,
  `rental_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `rental_details`
--

INSERT INTO `rental_details` (`id`, `license_plate`, `price_per_day`, `vehicle_brand`, `vehicle_name`, `rental_id`) VALUES
(7, '30B2-67890', 150000, 'Honda', 'Air Blade 2022', 4),
(8, '30X1-44567', 120000, '	Yamaha', 'Janus', 4),
(9, '29B3-99887', 130000, 'Honda', 'Lead 2021', 4),
(10, '29E1-12345', 60000, 'Honda', 'Vision', 5),
(11, '29E2-12345', 70000, 'Honda', 'Air Blade', 5),
(12, '29B1-12345', 120000, 'Honda', 'Vision 2023', 5),
(13, '29Y1-23456', 100000, 'Yamaha', 'Sirius RC', 5),
(22, '	30G1-88992', 110000, '	Yamaha', 'Nouvo LX', 7),
(23, '29Z1-11456', 100000, 'Honda', 'Vision 2019', 7),
(24, '30B4-66112', 95000, 'Honda', 'Wave RSX FI', 7),
(25, '	30G1-88992', 110000, '	Yamaha', 'Nouvo LX', 8),
(26, '29Z1-11456', 100000, 'Honda', 'Vision 2019', 8);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `driver_license` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `identity_card` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','CUSTOMER','STAFF') DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`id`, `created_at`, `driver_license`, `email`, `full_name`, `identity_card`, `password`, `phone`, `role`, `updated_at`) VALUES
(1, '2025-04-12 11:52:36.000000', NULL, 'admin@example.com', 'Admin', NULL, '$2a$10$1BqEmDJCskfuylZk/UZcd.Dv3Kln77rKuh49Kx8tEPEK.MuQ7GbAO', '0123456789', 'ADMIN', '2025-04-12 11:52:36.000000'),
(2, '2025-04-12 11:52:36.000000', NULL, 'staff@example.com', 'Staff', NULL, '$2a$10$1w5N9Ig2kSC0wctB/DMQFOaGebcrcL5f9I7X66j9mIfdhPVM/MY6K', '0112233445', 'STAFF', '2025-04-12 11:52:36.000000'),
(11, '2025-04-13 14:14:48.000000', 'uploads/291cf636-4fee-4a8f-afee-d30f38d0930f_driver_license.jpg', 'pna120104@gmail.com', 'Phạm Nhật Anh', 'uploads/3101b3ed-13ff-4274-a3bc-e93d286db3d1_identity_card.jpg', '$2a$10$Puts5/AjIUqzXiiiqGxtr.Iw/GF.hiIDc9yzA2MOujdvBEBSj3skW', '0865380226', 'CUSTOMER', '2025-04-14 18:02:53.000000'),
(12, '2025-04-13 15:25:29.000000', 'uploads/3445f7ed-7394-49cd-82ce-cb8e2a5a8a67_driver_license.jpg', 'customer@example.com', 'Customer123', 'uploads/c9d2907b-da38-436c-afda-1c6d072f8dd0_identity_card.jpg', '$2a$10$Sx6Rg/7fXaotr3A1OS2Wn.fjem1xEexSoNz9X7v9kx3gtyIobCvhC', '0987654339', 'CUSTOMER', '2025-04-14 17:35:47.000000'),
(13, '2025-04-13 17:32:41.000000', 'uploads/cb87c4aa-5b93-4eaa-9dbd-e096e06d94cc_driverLicense.jpg', 'ppap3537@gmail.com', 'phạm Nhật Anh', 'uploads/07895792-3a30-44eb-b125-02cf013423a9_identityCard.jpg', '$2a$10$z5/X0d3VcI32fm1wLSVo/.hok85Nflk2AZ9LrMzYWJKZuKIq39S.u', '0988765439', 'CUSTOMER', '2025-04-13 17:32:41.000000'),
(14, '2025-04-13 20:04:14.000000', 'uploads/40377d0d-811c-41f0-924a-38bf7d82b38d_driverLicense.jpg', 'pna123@gmail.com', 'Phạm Phạm', 'uploads/bf376315-000a-4412-bbba-ac339a96d0d7_identityCard.jpg', '$2a$10$OqiwWT1vFNFDo48oGXpFJeBEl843e9A/yO6eqeqNesrBWw0hCvevy', '0987674532', 'CUSTOMER', '2025-04-13 20:04:14.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vehicles`
--

CREATE TABLE `vehicles` (
  `id` bigint(20) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `license_plate` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price_per_day` double NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `vehicles`
--

INSERT INTO `vehicles` (`id`, `brand`, `created_at`, `description`, `license_plate`, `name`, `price_per_day`, `updated_at`) VALUES
(1, 'Honda', '2025-04-12 12:01:28.000000', 'Xe ga Honda Vision đời mới', '29E1-12345', 'Vision', 60000, '2025-04-12 12:01:28.000000'),
(2, 'Honda', '2025-04-12 12:02:35.000000', 'Xe ga Honda AirBlade 2025', '29E2-12345', 'Air Blade', 70000, '2025-04-12 12:02:35.000000'),
(3, 'Honda', '2025-04-12 14:54:18.000000', 'Xe tay ga tiết kiệm xăng, phù hợp nữ giới', '29B1-12345', 'Vision 2023', 120000, '2025-04-12 14:54:18.000000'),
(4, 'Honda', '2025-04-12 14:54:54.000000', 'Xe tay ga thể thao, động cơ khỏe', '30B2-67890', 'Air Blade 2022', 150000, '2025-04-12 14:54:54.000000'),
(5, 'Yamaha', '2025-04-12 14:55:27.000000', 'Xe số bền bỉ, thích hợp đi phượt', '29Y1-23456', 'Sirius RC', 100000, '2025-04-12 14:55:27.000000'),
(6, 'Honda', '2025-04-12 14:56:26.000000', 'Xe số đơn giản, dễ sử dụng', '29L1-11223', 'Wave Alpha 110cc', 90000, '2025-04-12 14:56:26.000000'),
(7, '	Yamaha', '2025-04-12 14:56:54.000000', 'Xe tay ga nhẹ nhàng, thời trang', '30X1-44567', 'Janus', 120000, '2025-04-12 14:56:54.000000'),
(8, 'Honda', '2025-04-12 14:57:24.000000', 'Cốp rộng, thích hợp cho nữ đi làm', '29B3-99887', 'Lead 2021', 130000, '2025-04-12 14:57:24.000000'),
(9, '	Yamaha', '2025-04-12 14:57:49.000000', 'Xe tay ga cũ, động cơ mạnh mẽ', '	30G1-88992', 'Nouvo LX', 110000, '2025-04-12 14:57:49.000000'),
(10, 'Honda', '2025-04-12 14:58:28.000000', 'Mẫu cũ, tiết kiệm xăng, dễ lái', '29Z1-11456', 'Vision 2019', 100000, '2025-04-12 14:58:28.000000'),
(11, 'Honda', '2025-04-12 14:58:57.000000', '	Xe số đời mới, vận hành êm ái', '29K1-33445', 'Future 125 FI', 110000, '2025-04-12 14:58:57.000000'),
(12, 'Honda', '2025-04-12 14:59:45.000000', 'Xe số tiết kiệm xăng, bền bỉ', '30B4-66112', 'Wave RSX FI', 95000, '2025-04-12 14:59:45.000000'),
(13, 'Honda', '2025-04-12 15:00:23.000000', 'Xe tay ga cao cấp, cốp rộng, êm ái', '30B4-66112', 'Grande', 95000, '2025-04-12 15:00:23.000000'),
(14, 'Honda', '2025-04-12 15:00:53.000000', 'Xe tay ga cao cấp, thời trang và mạnh mẽ', '30A1-55221', 'SH Mode', 180000, '2025-04-12 15:00:53.000000'),
(15, 'Honda', '2025-04-12 15:01:38.000000', 'Xe mới, động cơ êm, phù hợp thành phố', '29T1-22334', 'Vision 2022', 115000, '2025-04-12 15:01:38.000000'),
(16, 'Honda', '2025-04-12 15:08:42.000000', 'Mạnh mẽ, thiết kế thể thao', '30C1-66443', 'Air Blade 125cc', 140000, '2025-04-12 15:08:42.000000'),
(17, '	Yamaha', '2025-04-12 15:09:16.000000', 'Xe số mạnh mẽ, phù hợp đường dài', '29D1-12398', 'Jupiter FI', 105000, '2025-04-12 15:09:16.000000'),
(18, 'Honda', '2025-04-12 15:09:51.000000', 'Tay ga Thái, kiểu dáng thể thao', '30H1-98765', 'Click 125i', 135000, '2025-04-12 15:09:51.000000'),
(19, 'Honda', '2025-04-12 15:10:23.000000', 'Tay ga phân khối lớn, chạy xa ổn định', '29E1-77889', 'PCX 150cc', 170000, '2025-04-12 15:10:23.000000'),
(20, 'Honda', '2025-04-12 15:10:48.000000', 'Xe số nhập khẩu, vận hành mượt mà', '30Z1-33567', 'Wave Thái 110ccc', 100000, '2025-04-14 12:55:40.000000'),
(21, 'Yamaha', '2025-04-12 15:11:13.000000', 'Tay ga nhỏ gọn, dễ điều khiển', '29Y2-44551', 'Mio Classico', 95000, '2025-04-12 15:11:13.000000'),
(22, 'Honda', '2025-04-12 15:11:45.000000', 'Tay ga thể thao, mạnh, kiểu dáng đẹp', '30M1-55667', 'Vario 150cc', 160000, '2025-04-12 15:11:45.000000');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `vehicle_images`
--

CREATE TABLE `vehicle_images` (
  `id` bigint(20) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `vehicle_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `vehicle_images`
--

INSERT INTO `vehicle_images` (`id`, `url`, `vehicle_id`) VALUES
(1, 'uploads/c38f8c1c-bfa7-456b-aba6-5a1b4646a936_download.jpg', 1),
(2, 'uploads/f425535f-50e1-43a7-b009-cbd1cc72cb54_download.jpg', 1),
(3, 'uploads/d7b0fca2-41b4-42a6-8251-6cf716e7e914_download.jpg', 2),
(4, 'uploads/26be6b6b-19f2-4f26-8499-9c6307727373_download.jpg', 2),
(5, 'uploads/fe32d598-3b33-4b84-b3bd-07e034c13da6_download.jpg', 2),
(6, 'uploads/c9511bd5-b669-48bc-8297-39d37623c087_download.jpg', 3),
(7, 'uploads/624323c4-8811-456e-88ad-03c70e1156a2_download.jpg', 3),
(8, 'uploads/5463b84e-a7c0-48d6-b92f-75cd6250efb5_download.jpg', 3),
(9, 'uploads/4855f9d5-0694-4064-9352-766ea2ef378f_download.jpg', 4),
(10, 'uploads/c174ee6c-a9ea-4392-8d58-7f1a4d6b8a85_download.jpg', 4),
(11, 'uploads/6e91ff16-0fee-44d2-a600-601b11e2e19e_download.jpg', 4),
(12, 'uploads/dbf65276-c4af-4e51-ac07-9dc097c47009_download.jpg', 5),
(13, 'uploads/18142d67-fe23-4691-a255-803009933842_download.jpg', 6),
(14, 'uploads/814eb1cd-b636-4c70-a09d-6f9509684673_download.jpg', 7),
(15, 'uploads/9eecbdc6-0d97-40a2-a08b-d9098e5b7afe_download.jpg', 8),
(16, 'uploads/50e27387-4a93-46cb-b514-5de31fbc4cda_download.jpg', 8),
(17, 'uploads/64b018f3-8212-4eec-b6a6-76101ea3d2b7_download.jpg', 9),
(18, 'uploads/ee47e7df-5c63-4054-bc3c-1f71d94ef5eb_download.jpg', 10),
(19, 'uploads/ce720184-fa2e-4ebd-afbb-e33abe25b7f4_download.jpg', 10),
(20, 'uploads/609f343e-41b2-41fd-88b2-56346bc96a90_download.jpg', 11),
(21, 'uploads/ddd3843c-b9ab-4ee7-8563-0fefe8712b83_download.jpg', 11),
(22, 'uploads/40d1c5de-cf50-462c-b760-2d4ed93725d9_download.jpg', 12),
(23, 'uploads/81113d25-182a-4b5a-92bd-172c0ee32388_download.jpg', 12),
(24, 'uploads/c06c9c15-0e5f-493a-83e3-5b09deea9f85_download.jpg', 13),
(25, 'uploads/fe34b111-2f50-409e-9a81-dfaf13a43b7c_download.jpg', 13),
(26, 'uploads/f03d5862-ca65-453d-8d04-3455226b957b_download.jpg', 14),
(27, 'uploads/74ba02fe-5695-411b-9b6e-1cfc8f1652e9_download.jpg', 14),
(28, 'uploads/a6e679b4-ca99-4c45-8036-a9ed8945329a_download.jpg', 15),
(29, 'uploads/ff859d42-8b30-494e-8e64-41a53f64f8d5_download.jpg', 15),
(30, 'uploads/237fce77-ef4c-46e9-82e0-15a531b724ac_download.jpg', 16),
(31, 'uploads/e96199ab-6020-4228-a691-314ddd60aeca_download.jpg', 16),
(32, 'uploads/03e6a9ac-850d-4165-9c65-fbb333c8a53b_download.jpg', 16),
(33, 'uploads/cf36f60f-99fe-4212-a4fd-508e5609e7f4_download.jpg', 16),
(34, 'uploads/b8a237e6-46e9-4240-8e6e-d1aa457c0056_download.jpg', 17),
(35, 'uploads/4ec3841b-c4ce-43c1-a94f-17824bb0f426_download.jpg', 17),
(36, 'uploads/d07255a9-7c62-4d06-83dc-43dae115563c_download.jpg', 17),
(37, 'uploads/51816b2e-c6ff-4931-9e6a-3e657ff213d2_download.jpg', 17),
(38, 'uploads/8a21e34a-6889-47a7-bffb-1c9d25e1dd9d_download.jpg', 18),
(39, 'uploads/87aeb5a3-6fa5-4a5a-b349-718d3c5bfa7c_download.jpg', 18),
(40, 'uploads/285258fa-fa33-47ba-a37c-3940643175aa_download.jpg', 18),
(41, 'uploads/32509f2f-8986-472e-a9b6-01be600a5aad_download.jpg', 18),
(42, 'uploads/251325a0-5a71-45fb-8409-5af01365765d_download.jpg', 19),
(43, 'uploads/06a3a9be-54a2-45c7-8481-892cdd4c5c34_download.jpg', 19),
(44, 'uploads/9ad911af-1546-435d-8270-b6f5dda30103_download.jpg', 19),
(45, 'uploads/06491d6d-5391-441a-9d13-d34eb02d025b_download.jpg', 19),
(46, 'uploads/a570c802-d87c-4fa5-854b-f18bebeacbfa_download.jpg', 20),
(47, 'uploads/3e5c1806-8972-4f3b-b063-5fb8d94487f0_download.jpg', 20),
(48, 'uploads/3df6816c-9f23-4b0e-8935-0eb5f5efcdb2_download.jpg', 20),
(49, 'uploads/2fc9a3f8-0d17-47ab-86af-6a51574f4306_download.jpg', 21),
(50, 'uploads/b4f36a0d-2cf7-40d5-8ab1-d03f8011149c_download.jpg', 21),
(51, 'uploads/46c801c6-7ba5-43dc-88a5-3ca5fc440d8f_download.jpg', 21),
(52, 'uploads/b59e18cc-dd55-415e-890d-0568d44ac0c5_download.jpg', 21),
(53, 'uploads/ea00db49-89a2-45b6-8c4f-96fcd8f269a9_download.jpg', 22),
(54, 'uploads/4ed3a0ed-3aff-4af2-8caf-28ebc026b918_download.jpg', 22),
(55, 'uploads/ca74af84-62ac-45ea-b6d3-0415e3beab34_download.jpg', 22);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `audit_logs`
--
ALTER TABLE `audit_logs`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9y21adhxn0ayjhfocscqox7bh` (`user_id`);

--
-- Chỉ mục cho bảng `rentals`
--
ALTER TABLE `rentals`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtnhd1objf2mlb6ag6k726u269` (`user_id`);

--
-- Chỉ mục cho bảng `rental_details`
--
ALTER TABLE `rental_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK86d4flmq4rmbkrcplvv4irsxq` (`rental_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `vehicle_images`
--
ALTER TABLE `vehicle_images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKp6gw8mt61ktmsk5nuc4qid7i8` (`vehicle_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `audit_logs`
--
ALTER TABLE `audit_logs`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=155;

--
-- AUTO_INCREMENT cho bảng `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `rentals`
--
ALTER TABLE `rentals`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `rental_details`
--
ALTER TABLE `rental_details`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT cho bảng `vehicles`
--
ALTER TABLE `vehicles`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT cho bảng `vehicle_images`
--
ALTER TABLE `vehicle_images`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `notifications`
--
ALTER TABLE `notifications`
  ADD CONSTRAINT `FK9y21adhxn0ayjhfocscqox7bh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `rentals`
--
ALTER TABLE `rentals`
  ADD CONSTRAINT `FKtnhd1objf2mlb6ag6k726u269` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Các ràng buộc cho bảng `rental_details`
--
ALTER TABLE `rental_details`
  ADD CONSTRAINT `FK86d4flmq4rmbkrcplvv4irsxq` FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`id`);

--
-- Các ràng buộc cho bảng `vehicle_images`
--
ALTER TABLE `vehicle_images`
  ADD CONSTRAINT `FKp6gw8mt61ktmsk5nuc4qid7i8` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
