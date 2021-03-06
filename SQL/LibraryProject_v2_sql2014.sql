USE [master]
GO
/****** Object:  Database [LibraryProject_v2]    Script Date: 09.06.2021 09:31:55 ******/
CREATE DATABASE [LibraryProject_v2]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'LibraryProject_v2', FILENAME = N'D:\SQL Server 2017\MSSQL14.MSSQLSERVER\MSSQL\DATA\LibraryProject_v2.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'LibraryProject_v2_log', FILENAME = N'D:\SQL Server 2017\MSSQL14.MSSQLSERVER\MSSQL\DATA\LibraryProject_v2_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [LibraryProject_v2].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [LibraryProject_v2] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET ARITHABORT OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [LibraryProject_v2] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [LibraryProject_v2] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET  DISABLE_BROKER 
GO
ALTER DATABASE [LibraryProject_v2] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [LibraryProject_v2] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET RECOVERY FULL 
GO
ALTER DATABASE [LibraryProject_v2] SET  MULTI_USER 
GO
ALTER DATABASE [LibraryProject_v2] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [LibraryProject_v2] SET DB_CHAINING OFF 
GO
ALTER DATABASE [LibraryProject_v2] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [LibraryProject_v2] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [LibraryProject_v2] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'LibraryProject_v2', N'ON'
GO
USE [LibraryProject_v2]
GO
/****** Object:  Table [dbo].[Borrowings]    Script Date: 09.06.2021 09:31:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Borrowings](
	[borrowing_id] [int] IDENTITY(1,1) NOT NULL,
	[element_id] [int] NOT NULL,
	[borrowing_date] [date] NOT NULL,
	[status_id] [int] NOT NULL,
	[library_user_id] [int] NOT NULL,
 CONSTRAINT [PK_Borrowings] PRIMARY KEY CLUSTERED 
(
	[borrowing_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Librarian]    Script Date: 09.06.2021 09:31:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Librarian](
	[librarian_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[surname] [nvarchar](100) NOT NULL,
	[login] [nvarchar](20) NOT NULL,
	[password] [nvarchar](20) NOT NULL,
	[account_type] [int] NOT NULL,
 CONSTRAINT [PK_Librarian] PRIMARY KEY CLUSTERED 
(
	[librarian_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Library_element]    Script Date: 09.06.2021 09:31:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Library_element](
	[library_element_id] [int] IDENTITY(1,1) NOT NULL,
	[title] [varchar](200) NOT NULL,
	[type_id] [int] NOT NULL,
	[sort_id] [int] NOT NULL,
	[pages_number] [int] NULL,
	[duration_time] [int] NULL,
	[status_id] [int] NOT NULL,
 CONSTRAINT [PK_Library_element] PRIMARY KEY CLUSTERED 
(
	[library_element_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Library_element_sort]    Script Date: 09.06.2021 09:31:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Library_element_sort](
	[library_element_sort_id] [int] IDENTITY(1,1) NOT NULL,
	[sort] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Library_element_sort] PRIMARY KEY CLUSTERED 
(
	[library_element_sort_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Library_element_type]    Script Date: 09.06.2021 09:31:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Library_element_type](
	[library_element_type_id] [int] IDENTITY(1,1) NOT NULL,
	[type] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Library_element_type_id] PRIMARY KEY CLUSTERED 
(
	[library_element_type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Library_user]    Script Date: 09.06.2021 09:31:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Library_user](
	[library_user_id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[surname] [nvarchar](100) NOT NULL,
	[login] [nvarchar](20) NOT NULL,
	[password] [nvarchar](20) NOT NULL,
	[penalty] [smallmoney] NOT NULL,
	[account_type] [int] NOT NULL,
 CONSTRAINT [PK_Library_user_1] PRIMARY KEY CLUSTERED 
(
	[library_user_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Request]    Script Date: 09.06.2021 09:31:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Request](
	[request_id] [int] IDENTITY(1,1) NOT NULL,
	[borrowing_id] [int] NOT NULL,
	[librarian_id] [int] NOT NULL,
	[request_date] [date] NOT NULL,
	[status_id] [int] NOT NULL,
 CONSTRAINT [PK_Request] PRIMARY KEY CLUSTERED 
(
	[request_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Status]    Script Date: 09.06.2021 09:31:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[status_id] [int] IDENTITY(1,1) NOT NULL,
	[status_type] [varchar](20) NOT NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[status_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Librarian] ON 

INSERT [dbo].[Librarian] ([librarian_id], [name], [surname], [login], [password], [account_type]) VALUES (1, N'Jacek', N'Gos', N'jg93', N'pwd', 1)
INSERT [dbo].[Librarian] ([librarian_id], [name], [surname], [login], [password], [account_type]) VALUES (13, N'Dominik', N'Gos', N'dominik.gos', N'password', 1)
INSERT [dbo].[Librarian] ([librarian_id], [name], [surname], [login], [password], [account_type]) VALUES (14, N'Wojtek', N'Gos', N'wojtek.gos', N'password', 1)
INSERT [dbo].[Librarian] ([librarian_id], [name], [surname], [login], [password], [account_type]) VALUES (15, N'Szymon', N'Gos', N'szymon.gos', N'password', 1)
INSERT [dbo].[Librarian] ([librarian_id], [name], [surname], [login], [password], [account_type]) VALUES (16, N'Szymon', N'GOs', N'szymon.gOs', N'password', 1)
INSERT [dbo].[Librarian] ([librarian_id], [name], [surname], [login], [password], [account_type]) VALUES (17, N'Szymon', N'Gos', N'szymon.gos', N'password', 1)
INSERT [dbo].[Librarian] ([librarian_id], [name], [surname], [login], [password], [account_type]) VALUES (18, N'Bogdan1xd', N'Iksdee', N'bogdan1xd.iksdee', N'password', 1)
INSERT [dbo].[Librarian] ([librarian_id], [name], [surname], [login], [password], [account_type]) VALUES (19, N'Jacek', N'Gos', N'jacek.2.gos', N'password', 1)
INSERT [dbo].[Librarian] ([librarian_id], [name], [surname], [login], [password], [account_type]) VALUES (22, N'Jan', N'Kowalski', N'jan.kowalski', N'password', 1)
SET IDENTITY_INSERT [dbo].[Librarian] OFF
GO
SET IDENTITY_INSERT [dbo].[Library_element_sort] ON 

INSERT [dbo].[Library_element_sort] ([library_element_sort_id], [sort]) VALUES (1, N'Historyczne')
INSERT [dbo].[Library_element_sort] ([library_element_sort_id], [sort]) VALUES (2, N'Fantastyka')
INSERT [dbo].[Library_element_sort] ([library_element_sort_id], [sort]) VALUES (3, N'Kryminał')
INSERT [dbo].[Library_element_sort] ([library_element_sort_id], [sort]) VALUES (4, N'Edukacja')
INSERT [dbo].[Library_element_sort] ([library_element_sort_id], [sort]) VALUES (5, N'Technologie')
SET IDENTITY_INSERT [dbo].[Library_element_sort] OFF
GO
SET IDENTITY_INSERT [dbo].[Library_element_type] ON 

INSERT [dbo].[Library_element_type] ([library_element_type_id], [type]) VALUES (1, N'Książka')
INSERT [dbo].[Library_element_type] ([library_element_type_id], [type]) VALUES (2, N'Film')
INSERT [dbo].[Library_element_type] ([library_element_type_id], [type]) VALUES (3, N'Czasopismo')
SET IDENTITY_INSERT [dbo].[Library_element_type] OFF
GO
SET IDENTITY_INSERT [dbo].[Library_user] ON 

INSERT [dbo].[Library_user] ([library_user_id], [name], [surname], [login], [password], [penalty], [account_type]) VALUES (2, N'Bogdan', N'Smoleń', N'bogdan.smoleń', N'password', 0.0000, 2)
SET IDENTITY_INSERT [dbo].[Library_user] OFF
GO
ALTER TABLE [dbo].[Borrowings]  WITH CHECK ADD  CONSTRAINT [FK_Borrowings_Library_element] FOREIGN KEY([element_id])
REFERENCES [dbo].[Library_element] ([library_element_id])
GO
ALTER TABLE [dbo].[Borrowings] CHECK CONSTRAINT [FK_Borrowings_Library_element]
GO
ALTER TABLE [dbo].[Borrowings]  WITH CHECK ADD  CONSTRAINT [FK_Borrowings_Library_user1] FOREIGN KEY([library_user_id])
REFERENCES [dbo].[Library_user] ([library_user_id])
GO
ALTER TABLE [dbo].[Borrowings] CHECK CONSTRAINT [FK_Borrowings_Library_user1]
GO
ALTER TABLE [dbo].[Borrowings]  WITH CHECK ADD  CONSTRAINT [FK_Borrowings_Status] FOREIGN KEY([status_id])
REFERENCES [dbo].[Status] ([status_id])
GO
ALTER TABLE [dbo].[Borrowings] CHECK CONSTRAINT [FK_Borrowings_Status]
GO
ALTER TABLE [dbo].[Library_element]  WITH CHECK ADD  CONSTRAINT [FK_Library_element_Library_element_sort] FOREIGN KEY([sort_id])
REFERENCES [dbo].[Library_element_sort] ([library_element_sort_id])
GO
ALTER TABLE [dbo].[Library_element] CHECK CONSTRAINT [FK_Library_element_Library_element_sort]
GO
ALTER TABLE [dbo].[Library_element]  WITH CHECK ADD  CONSTRAINT [FK_Library_element_Library_element_type_id] FOREIGN KEY([type_id])
REFERENCES [dbo].[Library_element_type] ([library_element_type_id])
GO
ALTER TABLE [dbo].[Library_element] CHECK CONSTRAINT [FK_Library_element_Library_element_type_id]
GO
ALTER TABLE [dbo].[Library_element]  WITH CHECK ADD  CONSTRAINT [FK_Library_element_Status] FOREIGN KEY([status_id])
REFERENCES [dbo].[Status] ([status_id])
GO
ALTER TABLE [dbo].[Library_element] CHECK CONSTRAINT [FK_Library_element_Status]
GO
ALTER TABLE [dbo].[Request]  WITH CHECK ADD  CONSTRAINT [FK_Request_Borrowings] FOREIGN KEY([borrowing_id])
REFERENCES [dbo].[Borrowings] ([borrowing_id])
GO
ALTER TABLE [dbo].[Request] CHECK CONSTRAINT [FK_Request_Borrowings]
GO
ALTER TABLE [dbo].[Request]  WITH CHECK ADD  CONSTRAINT [FK_Request_Librarian] FOREIGN KEY([librarian_id])
REFERENCES [dbo].[Librarian] ([librarian_id])
GO
ALTER TABLE [dbo].[Request] CHECK CONSTRAINT [FK_Request_Librarian]
GO
ALTER TABLE [dbo].[Request]  WITH CHECK ADD  CONSTRAINT [FK_Request_Status] FOREIGN KEY([status_id])
REFERENCES [dbo].[Status] ([status_id])
GO
ALTER TABLE [dbo].[Request] CHECK CONSTRAINT [FK_Request_Status]
GO
USE [master]
GO
ALTER DATABASE [LibraryProject_v2] SET  READ_WRITE 
GO
