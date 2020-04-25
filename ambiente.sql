-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-04-2020 a las 21:35:33
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.2.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ambiente`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `Id_admin` int(11) NOT NULL,
  `nombreA` text NOT NULL,
  `telefonoA` text NOT NULL,
  `idusuarioa` int(11) NOT NULL,
  `idtarroa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante`
--

CREATE TABLE `estudiante` (
  `idestudiante` int(11) NOT NULL,
  `programa` text NOT NULL,
  `nombre` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estudiante`
--

INSERT INTO `estudiante` (`idestudiante`, `programa`, `nombre`) VALUES
(0, 'jose', 'jose'),
(987, 'karate', 'jose'),
(1234, 'ambiental', 'alfred'),
(9383, 'ambiental', 'Juan'),
(10291, 'ingenieria', 'algien'),
(12345, '123345', '125'),
(2154233, 'multimedia', 'giron'),
(2156633, 'multimedia', 'jose');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL,
  `nombreproducto` text NOT NULL,
  `tipo` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `idprofesor` int(11) NOT NULL,
  `facultad` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarro`
--

CREATE TABLE `tarro` (
  `idtarro` int(11) NOT NULL,
  `contenido` text NOT NULL,
  `imagen` binary(11) NOT NULL,
  `ubicacion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipotarro`
--

CREATE TABLE `tipotarro` (
  `idtipotarro` int(11) NOT NULL,
  `idtarrot` int(11) NOT NULL,
  `idproductot` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE `tipousuario` (
  `idtipouser` int(11) NOT NULL,
  `idprofesoru` int(11) NOT NULL,
  `idestudianteu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` int(11) NOT NULL,
  `apellido` text NOT NULL,
  `telefonoU` int(11) NOT NULL,
  `idtipouseru` int(11) NOT NULL,
  `idproductou` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`Id_admin`),
  ADD KEY `idtarroa` (`idtarroa`),
  ADD KEY `idusuarioa` (`idusuarioa`);

--
-- Indices de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD PRIMARY KEY (`idestudiante`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idproducto`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`idprofesor`);

--
-- Indices de la tabla `tarro`
--
ALTER TABLE `tarro`
  ADD PRIMARY KEY (`idtarro`);

--
-- Indices de la tabla `tipotarro`
--
ALTER TABLE `tipotarro`
  ADD PRIMARY KEY (`idtipotarro`),
  ADD KEY `idtarrot` (`idtarrot`),
  ADD KEY `idproductot` (`idproductot`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`idtipouser`),
  ADD KEY `idprofesor` (`idprofesoru`),
  ADD KEY `idestudiante` (`idestudianteu`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD KEY `idtipouser` (`idtipouseru`),
  ADD KEY `idproductou` (`idproductou`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `administrador_ibfk_1` FOREIGN KEY (`idtarroa`) REFERENCES `tarro` (`idtarro`),
  ADD CONSTRAINT `administrador_ibfk_2` FOREIGN KEY (`idusuarioa`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `tipotarro`
--
ALTER TABLE `tipotarro`
  ADD CONSTRAINT `tipotarro_ibfk_1` FOREIGN KEY (`idtarrot`) REFERENCES `tarro` (`idtarro`),
  ADD CONSTRAINT `tipotarro_ibfk_2` FOREIGN KEY (`idproductot`) REFERENCES `producto` (`idproducto`);

--
-- Filtros para la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD CONSTRAINT `tipousuario_ibfk_1` FOREIGN KEY (`idprofesoru`) REFERENCES `profesor` (`idprofesor`),
  ADD CONSTRAINT `tipousuario_ibfk_2` FOREIGN KEY (`idestudianteu`) REFERENCES `estudiante` (`idestudiante`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`idtipouseru`) REFERENCES `tipousuario` (`idtipouser`),
  ADD CONSTRAINT `usuario_ibfk_2` FOREIGN KEY (`idproductou`) REFERENCES `producto` (`idproducto`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
