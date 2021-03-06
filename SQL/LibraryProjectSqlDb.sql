USE [LibraryProject_v2]
GO
/****** Object:  Table [dbo].[Borrowings]    Script Date: 03.06.2021 17:02:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Borrowings](
	[borrowing_id] [int] NOT NULL IDENTITY(1, 1),
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
/****** Object:  Table [dbo].[Librarian]    Script Date: 03.06.2021 17:02:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Librarian](
	[librarian_id] [int] NOT NULL IDENTITY(1, 1),
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
/****** Object:  Table [dbo].[Library_element]    Script Date: 03.06.2021 17:02:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Library_element](
	[library_element_id] [int] NOT NULL IDENTITY(1, 1),
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
/****** Object:  Table [dbo].[Library_element_sort]    Script Date: 03.06.2021 17:02:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Library_element_sort](
	[Library_element_sort_id] [int] NOT NULL IDENTITY(1, 1),
	[sort] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Library_element_sort] PRIMARY KEY CLUSTERED 
(
	[Library_element_sort_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Library_element_type_id]    Script Date: 03.06.2021 17:02:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Library_element_type_id](
	[library_element_type_id] [int] NOT NULL IDENTITY(1, 1),
	[type] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Library_element_type_id] PRIMARY KEY CLUSTERED 
(
	[library_element_type_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Library_user]    Script Date: 03.06.2021 17:02:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Library_user](
	[library_user_id] [int] NOT NULL IDENTITY(1, 1),
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
/****** Object:  Table [dbo].[Request]    Script Date: 03.06.2021 17:02:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Request](
	[request_id] [int] NOT NULL IDENTITY(1, 1),
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
/****** Object:  Table [dbo].[Status]    Script Date: 03.06.2021 17:02:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Status](
	[status_id] [int] NOT NULL IDENTITY(1, 1),
	[status_type] [varchar](20) NOT NULL,
 CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED 
(
	[status_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
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
REFERENCES [dbo].[Library_element_sort] ([Library_element_sort_id])
GO
ALTER TABLE [dbo].[Library_element] CHECK CONSTRAINT [FK_Library_element_Library_element_sort]
GO
ALTER TABLE [dbo].[Library_element]  WITH CHECK ADD  CONSTRAINT [FK_Library_element_Library_element_type_id] FOREIGN KEY([type_id])
REFERENCES [dbo].[Library_element_type_id] ([library_element_type_id])
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
