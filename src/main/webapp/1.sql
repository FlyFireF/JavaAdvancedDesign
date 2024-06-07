use dsscm;
insert into tb_order(id, userName, customerPhone, userAddress, proCount, cost, serialNumber)
values (10,'张九','18888888888','安徽',2,20.00,'AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAC');
insert into tb_order_detail(id, orderId, productId, quantity, cost)
values (23,10,22,2,20.00);