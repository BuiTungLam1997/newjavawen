INSERT INTO `newjavaweb`.`role` (`name`, `code`) VALUES ('Quản trị', 'admin');
INSERT INTO `newjavaweb`.`role` (`name`, `code`) VALUES ('Người dùng', 'user');

INSERT INTO `newjavaweb`.`user` (`username`, `password`, `fullname`, `roleid`, `status`) VALUES ('admin', '123456', 'admin', '1', '1');
INSERT INTO `newjavaweb`.`user` (`username`, `password`, `fullname`, `roleid`, `status`) VALUES ('nguyenvana', '123456', 'user', '2', '1');
INSERT INTO `newjavaweb`.`user` (`username`, `password`, `fullname`, `roleid`, `status`) VALUES ('nguyenvanb', '123456', 'user', '2', '1');
