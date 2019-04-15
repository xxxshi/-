-- drop database erp;
create database erp;
use erp;
show tables;
#公司登录信息表
drop table if exists companyMes;
create table companyMes(
companyCode varchar(15) primary key comment '公司编号',
companyAddress varchar(50) not null comment '公司所在地址',
companyName varchar(20) not null comment '公司名',
companyLoginName varchar(20) not null comment '公司登录账户',
loginPassword varchar(15) not null comment '登录密码'
);
insert into companyMes values('045001','福建省闽侯县上街镇','隆楷纸业','admin','admin');
#公司信息表
drop table if exists SupplyCustomerMes;
create table SupplyCustomerMes(
companyNumber varchar(15) primary key comment '公司编号',
companyCode varchar(15) comment '公司编号外键，用于确认公司客户以及供应商',
companyName varchar(20) not null comment '公司名',
companyAddreee varchar(15) not null comment '公司地址',
companyType varchar(2) not null comment '0代表供应商 1代表客户'
);
#采购计划
drop table if exists purchaseplan;
create table purchaseplan(
contractCode varchar(20) primary key comment'合同号',
contractDate datetime not null comment'合同日期',
supply varchar(20) comment'供应商',
totalPrice numeric(12,2) comment '拟定合同总价'
);
select * from purchaseplan;

#采购计划详单
drop table if exists purchaseplanDetail;
create table purchaseplanDetail(
contractCode varchar(20) comment '合同号外键',
materialCode varchar(15) comment'物料编码',
quantity int comment'件数',
weight numeric(6,4) not null comment '重量',
totalprice numeric(8,2) comment'总价',
transportationCose numeric(6,2) comment'运费',
lowerQuantity int comment '最低库存',
higherQuantity int comment '最高库存'
);
select * from purchaseplanDetail;

#采购表
drop table if exists purchase;
create table purchase(
purchaseid varchar(20) primary key, #采购单号
orderDate date not null comment '订单日期',
contractCode varchar(20) comment '采购计划合同号',
totalPrice numeric(12,2) not null comment '订单总价',
totalWeight numeric(7,4) not null comment '订单总重',
totalNum int not null comment '订单总数量'
);
#进仓验货单,
drop table if exists examinegood;
create table examinegood(
storageinid varchar(20) primary key comment'进仓单号',
supply varchar(20) comment'供应商',
in_date datetime comment'进仓日期',
purchaseid varchar(20) comment'采购单号',
receivetime datetime comment '签收时间',
storehousePeople varchar(8) comment '仓管员姓名',
financePeople varchar(8) comment '财务',
logisticsControl varchar(8) comment '物流控制员'
);
select * from examinegood;


#进仓验货单详单
drop table if exists examinegoodDetail;
create table examinegoodDetail(
pieceid varchar(20) primary key comment'件号',
storageinid varchar(20) ,
materialCode varchar(15) ,
location varchar(20) comment '库号',
proved varchar(2) comment '货品合格(0表示不合格，1表示合格)',
weight numeric(6,4) comment '重量'
);
select * from examinegooddetail;
#进仓表
drop table if exists storagein;
create table storagein(
        storageinid varchar(20) primary key, #进仓单号
        supply varchar(20) comment'供应商',
        receiveCompany varchar(15) comment '收货公司名',
        purchaseid varchar(20) , #采购单号
in_date date  comment '进仓日期'
);

select * from storagein;


#进仓详单
drop table if exists storageinDetail;
create table storageinDetail(
        storageinid varchar(20) ,
        pieceid varchar(20)  comment'件号',
        instruction varchar(50) comment '备注'
);


#退货进仓单
drop table if exists storageout;
create table storageout(
        storageinid varchar(20) primary key, #进仓单号
        supply varchar(20) comment'供应商',
        receiveCompany varchar(15) comment '收货公司名',
        purchaseid varchar(20) , #采购单号
in_date date  comment '进仓日期'
);

#退货进仓单详单
drop table if exists storageoutDetail;
create table storageoutDetail(
        storageinid varchar(20) ,
        pieceid varchar(20)  comment'件号',
        instruction varchar(50) comment '备注'
);

#客诉报告表
drop table if exists goodcheck;
create table goodcheck(
goodcheckid varchar(20) primary key, #客诉报告编号
storageinid varchar(20) comment '进仓单号',
purchaseid varchar(20) comment '采购单号',
supply varchar(20) comment '供应商'
);
#客诉报告详情表
drop table if exists goodcheckdetail;
CREATE TABLE `goodcheckdetail` (
  `pieceid` varchar(20) DEFAULT NULL COMMENT '件号',
  `goodcheckid` varchar(20) NOT NULL COMMENT '外键：客诉报告编号',
  `coreweighe` decimal(7,2) DEFAULT NULL COMMENT '筒芯重量',
  `shortweight` decimal(7,2) DEFAULT NULL COMMENT '短重',
  `gramweight` decimal(7,2) DEFAULT NULL COMMENT '克重',
  `width` decimal(7,2) DEFAULT NULL COMMENT '幅宽',
  `outerpackagingloss` decimal(7,2) DEFAULT NULL COMMENT '外包装损耗',
  `paperdamage` decimal(7,2) DEFAULT NULL COMMENT '纸张破损',
  `Moisture` decimal(7,4) DEFAULT NULL COMMENT '水分',
  `coreweigheStatement` varchar(100) DEFAULT NULL COMMENT '筒芯重量描述',
  `shortweightStatement` varchar(100) DEFAULT NULL COMMENT '短重',
  `gramweightStatement` varchar(100) DEFAULT NULL COMMENT '克重',
  `widthStatement` varchar(100) DEFAULT NULL COMMENT '幅宽',
  `outerpackaginglossStatement` varchar(100) DEFAULT NULL COMMENT '外包装损耗',
  `paperdamageStatement` varchar(100) DEFAULT NULL COMMENT '纸张破损',
  `MoistureStatement` varchar(100) DEFAULT NULL COMMENT '水分'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


#采购单
CREATE TABLE `buy_price` (
  `materialCode` varchar(20) DEFAULT NULL COMMENT '物料编码',
  `price` decimal(8,2) DEFAULT NULL COMMENT '采购单价'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into buy_price values('001',1500);
insert into buy_price values('002',1500);
insert into buy_price values('003',1500);
insert into buy_price values('004',1500);
insert into buy_price values('005',1500);
insert into buy_price values('006',1500);




-- ----------------------------------------------------------------------------------------------------------------------------------------------



#订单总表
drop table if exists ordermaster;
create table ordermaster(
ordermasterid varchar(20) primary key,
ordertime datetime comment'订单日期',
suretime datetime comment '交货日期',
customer varchar(20) comment'客户名',
state int comment'状态'
);
-- insert into ordermaster values('1','2018-12-01','2018-12-04','小白',0);
-- insert into ordermaster values('2','2018-12-02','2018-12-04','小黑',0);

drop table if exists orderdetail;
#订单表
create table orderdetail(
ordermasterid varchar(20), #订单编号
itemid varchar(20) comment'物料编码',
brand varchar(20) comment'品牌',
papertype varchar(20) comment'纸种',
rank char(2) comment'级别',
gweight numeric(8,2) comment'克重',
specification varchar(10) comment'规格',
unit char(2) comment'单位',
producttype varchar(10) comment'产品类型',
quantity numeric(8,2) comment'数量',
state int comment'状态'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- insert into orderdetail values('1','1','晨鸣','白卡','A','300','1092','吨','原纸','0.50',0);
-- insert into orderdetail values('2','2','晨鸣','白卡','A','300','1092','吨','成品','1.62',0);
-- insert into orderdetail values('1','3','晨鸣','白卡','A','350','1092','吨','原纸','0.43',0);
-- insert into orderdetail values('2','4','晨鸣','白卡','A','350','1092','吨','成品','1.55',0);
-- insert into orderdetail values('1','5','晨鸣','白卡','A','400','1092','吨','原纸','0.22',0);
-- insert into orderdetail values('1','6','晨鸣','白卡','A','400','1092','吨','原纸','0.34',0);
-- #insert into orderdetail values('1','7','晨鸣','白卡','A','300','1092','吨','原纸','0.77');


#特价表
drop table if exists specialprice;
create table specialprice(
customer varchar(20) comment'客户名',
specialid varchar(20) primary key, #特价表号
itemid varchar(20) comment'物料编码',
affirmdate datetime comment'批准日期',
brand varchar(20) comment'品牌',
papertype varchar(20) comment'纸种',
rank char(2) comment'级别',
gweight numeric(8,2) comment'克重',
specification varchar(10) comment'规格',
unit char(2) comment'单位',
producttype varchar(10) comment'产品类型',
price numeric(8,2) comment'单价'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into specialprice() values('小白','1','001','2018-12-02','晨鸣','白卡','A','300','1092','吨','原纸','1300');
insert into specialprice() values('小白','2','002','2018-12-02','晨鸣','白卡','A','300','787*1092','吨','成品','1500');
insert into specialprice() values('小白','3','003','2018-12-02','晨鸣','白卡','A','350','1092','吨','原纸','1500');
insert into specialprice() values('小白','4','004','2018-12-02','晨鸣','白卡','A','350','787*1092','吨','成品','2000');
insert into specialprice() values('小白','5','005','2018-12-02','晨鸣','白卡','A','400','1092','吨','原纸','1750');
insert into specialprice() values('小白','6','006','2018-12-02','晨鸣','白卡','A','300','787*1092','吨','成品','2200');


drop table if exists sale_price;
#销售价格表
create table sale_price(
-- priceid varchar(20) primary key , #销售价格表号
itemid varchar(20) comment'物料编码',#物料编码
#affirmdate datetime comment'批准日期',
brand varchar(20) comment'品牌',
papertype varchar(20) comment'纸种',
rank char(2) comment'级别',
gweight numeric(8,2) comment'克重',
specification varchar(10) comment'规格',
unit char(2) comment'单位',
producttype varchar(10) comment'产品类型',
price numeric(8,2) comment'单价'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into sale_price()values('001','晨鸣','白卡','A','300','1092','吨','原纸','1500');
insert into sale_price()values('002','晨鸣','白卡','A','300','787*1092','吨','成品','2000');
insert into sale_price()values('003','晨鸣','白卡','A','350','1092','吨','原纸','1800');
insert into sale_price()values('004','晨鸣','白卡','A','350','787*1092','吨','成品','2200');
insert into sale_price()values('005','晨鸣','白卡','A','400','1092','吨','原纸','2000');
insert into sale_price()values('006','晨鸣','白卡','A','400','787*1092','吨','成品','2500');
-- delete from buy_price;
-- select * from sale_price;
-- select * from buy_price;

#非分切成品出仓单
drop table if exists ncut_outstorage;
create table ncut_outstorage(
outstorageid varchar(20) primary key, #出仓单号
saleid varchar(20) comment'销售单号',
brand varchar(20) comment'品牌',
customer varchar(20) comment '客户名',
papertype varchar(20) comment'纸种',
rank char(2) comment'级别',
gweight numeric(4,2) comment'克重',
specification varchar(10) comment'规格',
unit char(2) comment'单位',
producttype varchar(10) comment'产品类型',
quantity numeric(8,2) comment'数量',
price numeric(4,2) comment'单价',
cut_price numeric(4,2) comment'分切费',
total numeric(8,2) comment'总价'
);

#销售确认书总表
drop table if exists saleconfirmmaster;
create table saleconfirmmaster(
#saleid varchar(20) comment'销售单号',
ordermasterid varchar(20) comment'订单编号',
saledate datetime comment'订单日期',
suredate datetime comment'确认时间',
buy varchar(20) comment'买方',
sale varchar(20) comment'卖方',
price_cut numeric(8,2) comment'分切费',
total numeric(8,2) comment'总价',
state int comment'确认状态' default 0,
state_ok int comment'执行与否' default 0
);

#销售确认书
drop table if exists saleconfirm;
create table saleconfirm(
#saleid varchar(20), #销售单号
itemid varchar(20) comment'物料编码',
ordermasterid varchar(20) comment'订单编号',
brand varchar(20) comment'品牌',
papertype varchar(20) comment'纸种',
rank char(2) comment'级别',
gweight numeric(8,2) comment'克重',
specification varchar(10) comment'规格',
unit char(2) comment'单位',
producttype varchar(10) comment'产品类型',
quantity numeric(8,2) comment'数量',
price numeric(8,2) comment'单价',
state int comment'确认状态' default 0,
state_ok int comment'执行与否' default 0
);

#物料表
drop table if exists materiel;
create table materiel(
itemid varchar(20) primary key, #物料编码
brand varchar(20) comment'品牌',
papertype varchar(20) comment'纸种',
rank char(2) comment'级别',
gweight numeric(8,2) comment'克重',
specification varchar(10) comment'规格',
unit char(2) comment'单位',
producttype varchar(10) comment'产品类型',
orign varchar(20) comment'成品对应的原纸编码'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into materiel()values('001','晨鸣','白卡','A','300','1092','吨','原纸',null);
insert into materiel()values('002','晨鸣','白卡','A','300','787*1092','吨','成品','001');
insert into materiel()values('003','晨鸣','白卡','A','350','1092','吨','原纸',null);
 insert into materiel()values('004','晨鸣','白卡','A','350','787*1092','吨','成品','003');
 insert into materiel()values('005','晨鸣','白卡','A','400','1092','吨','原纸',null);
 insert into materiel()values('006','晨鸣','白卡','A','400','787*1092','吨','成品','005');
-- delete from materiel;
-- select * from materiel;


#分切成品出仓单明细表
drop table if exists cut_outstorage;
create table cut_outstorage(
ordermasterid varchar(20) comment'订单编号', #用于对应出仓主表
brand varchar(20) comment'品牌',
papertype varchar(20) comment'纸种',
rank char(2) comment'级别',
gweight numeric(8,2) comment'克重',
specification varchar(10) comment'规格',
unit char(2) comment'单位',
quantity numeric(8,2) comment'数量',
sheet_num int comment'张数',
price numeric(8,2) comment'单价',
price_single numeric(8,2) comment'金额'
);

#分切成品出仓单主表
drop table if exists cut_outstoragemaster;
create table cut_outstoragemaster(
outstorageid varchar(20), #出仓单号
ordermasterid varchar(20) comment'订单单号',
customer varchar(20) comment'客户',
out_date datetime comment'出仓日期',
order_date datetime comment'客户下单时间',
total numeric(8,2) comment'总价',
sheet_all int comment'张数',
quantity_all numeric(8,2) comment'总数'
);
select * from orderdetail;

#分切出仓单明细表
drop table if exists finishoutstorage;
create table finishoutstorage(
ordermasterid varchar(20) comment'订单编号', #用于对应出仓主表
pieceid varchar(20) comment'件号',
brand varchar(20) comment'品牌',
papertype varchar(20) comment'纸种',
rank char(2) comment'级别',
gweight numeric(8,2) comment'克重',
specification varchar(10) comment'规格',
unit char(2) comment'单位',
quantity numeric(8,2) comment'数量',
sheet_num int comment'张数',
price numeric(8,2) comment'单价',
price_single numeric(8,2) comment'金额'
);

#分切出仓单主表
drop table if exists finishoutstoragemaster;
create table finishoutstoragemaster(
outstorageid varchar(20), #出仓单号
ordermasterid varchar(20) comment'订单单号',
customer varchar(20) comment'客户',
out_date datetime comment'出仓日期',
order_date datetime comment'客户下单时间',
total numeric(8,2) comment'总价',
sheet_all int comment'张数',
quantity_all numeric(8,2) comment'总数',
price_cut numeric(8,2) comment'分切费'
);

#库存表
drop table if exists repetory;
create table repetory(
itemid varchar(20) primary key, #物料编码
location varchar(20) comment'库号',
brand varchar(20) comment'品牌',
papertype varchar(20) comment'纸种',
rank char(2) comment'级别',
gweight numeric(8,2) comment'克重',
specification varchar(10) comment'规格',
unit char(2) comment'单位',
producttype varchar(10) comment'产品类型',
quantity numeric(8,2) comment'数量'
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into repetory()values('001','A','晨鸣','白卡','A','300','1092','吨','原纸','1.2');
insert into repetory()values('002','A','晨鸣','白卡','A','300','787*1092','吨','成品','0.1');
insert into repetory()values('003','A','晨鸣','白卡','A','350','1092','吨','原纸','2.6');
insert into repetory()values('004','A','晨鸣','白卡','A','350','787*1092','吨','成品','0.1');
insert into repetory()values('005','A','晨鸣','白卡','A','400','1092','吨','原纸','2.6');
insert into repetory()values('006','A','晨鸣','白卡','A','400','787*1092','吨','原纸','3.2');

#非分切出仓单明细表
drop table if exists ncut_outstorage;
create table ncut_outstorage(
ordermasterid varchar(20) comment'订单编号', #用于对应出仓主表
brand varchar(20) comment'品牌',
papertype varchar(20) comment'纸种',
rank char(2) comment'级别',
gweight numeric(8,2) comment'克重',
specification varchar(10) comment'规格',
unit char(2) comment'单位',
quantity varchar(10) comment'数量',
price numeric(8,2) comment'单价',
price_single numeric(8,2) comment'单条总价'
);


#非分切出仓单主表
drop table if exists nfinishoutstorage;
create table nfinishoutstorage(
outstorageid varchar(20), #出仓单号
ordermasterid varchar(20) comment'订单单号',
customer varchar(20) comment'客户',
out_date datetime comment'出仓日期',
order_date datetime comment'客户下单时间',
total numeric(8,2) comment'总价'
);


#直送订单
drop table if exists directordermaster;
create table directordermaster(
ordermasterid varchar(20) primary key,
ordertime datetime comment'订单日期',
suretime datetime comment '交货日期',
customer varchar(20) comment'客户名',
state int 
);


#直送订单详单表
drop table if exists directorderdetail;
create table directorderdetail(
ordermasterid varchar(20), #订单编号
itemid varchar(20) comment'物料编码',
brand varchar(20) comment'品牌',
papertype varchar(20) comment'纸种',
rank char(2) comment'级别',
gweight numeric(8,2) comment'克重',
specification varchar(10) comment'规格',
unit char(2) comment'单位',
producttype varchar(10) comment'产品类型',
quantity numeric(8,2) comment'数量',
state int
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- ---------------------------------------------------------------------------------------------------------------------------------------------

#计算总价存储过程
drop procedure if exists countOrderTotalPrice;
CREATE  PROCEDURE countOrderTotalPrice( in contractCode varchar(20))
begin
  declare materialCode varchar(15) default null;#物料编号
  declare price numeric(7,2) default 0;#单价
  declare allcount int default 0;#总数
  declare i int default 0;
  declare totalprice numeric(12,2) default 0;#总价
  declare quantity int default 0;#单件数量
  declare transportationCost numeric(6,2) default 0;
  select count(*) into allcount from purchaseplanDetail where purchaseplanDetail.contractCode=contractCode;
  while i<allcount
  do
    select purchaseplanDetail.materialCode,purchaseplanDetail.quantity into materialCode,quantity  from purchaseplanDetail where purchaseplanDetail.contractCode=contractCode limit i,1;
    select buy_price.price into price  from buy_price where buy_price.materialCode=materialCode;
    update purchaseplanDetail set purchaseplanDetail.totalprice=price*quantity+transportationCose where purchaseplanDetail.contractCode=contractCode and purchaseplanDetail.materialCode=materialCode;
    select transportationCose into transportationCost from purchaseplanDetail where purchaseplanDetail.contractCode=contractCode and purchaseplanDetail.materialCode=materialCode;
    set totalprice=totalprice+price*quantity+transportationCost;
    set i=i+1;
  end while;
  update purchaseplan set purchaseplan.totalprice=totalprice where purchaseplan.contractCode=contractCode;
end;




drop procedure if exists createStorageinDetail;
create PROCEDURE createStorageinDetail(in storageinidIn varchar(20))
BEGIN
	
	DECLARE purchaseidTemp varchar(20); -- 由进程单号获得的采购单号 
	DECLARE contractCodeTemp varchar(20);		-- 由采购单号获得的采购计划合同号
	DECLARE total int;		-- 由采购计划合同号获得的物料种类数量
	declare materialCodeTemp varchar(20); -- 临时存储每种物料的编号
	declare size int default 0;-- 每种物料的件数    while循环所用的变量
	declare weightTemp NUMERIC(4,2) default 0;
	declare keyTemp varchar(20); -- 件号
	declare n int default 0; -- 拼接件号的增长数字
	declare nChar varchar(9); -- 拼接件号的增长数字转为字符串型
	declare i int default 0;-- while循环所用的变量
	declare j int default 0;-- while循环所用的变量
	select 	purchaseid into purchaseidTemp from examinegood where storageinid = storageinidIn;
	select contractCode into contractCodeTemp from purchase where purchaseid = purchaseidTemp;
	select count(contractCode) into total from  purchaseplanDetail where contractCode = contractCodeTemp;

	
	while i<total
	DO
		select materialCode,quantity,weight into materialCodeTemp,size,weightTemp from purchaseplanDetail limit i,1; -- 查询每种物料
		set weightTemp = weightTemp/size;
		while j<size
		DO
			select date_format(now(), '%Y%m%d') into keyTemp;
			select substring(keyTemp,3) into keyTemp;
			select count(*)+10 into n from examinegoodDetail where pieceid like concat(keyTemp,'%'); -- 根据当天已经插入的件号数量来给n赋值
			set n = n+1;
			SELECT CONCAT(n,'') into nChar;
			select CONCAT(keyTemp, nChar) into keyTemp; -- 生成件号
			
			insert into examinegoodDetail values(keyTemp,storageinidIn,materialCodeTemp,null,null,weightTemp);  -- 依次插入每种物料
			set j = j+1;
		end while; -- 内层循环结束，插入一种物料
		set j = 0;
		set i = i+1;
	end while;

end;


-- -------------------------------------------------------------------------------------
#存储过程：分切产品
drop procedure if exists cut_product;
create procedure cut_product()
begin
    declare itemid_ varchar(20); #物料编码
    declare n int; #出仓主表的行数
    declare shuliang_cut numeric(8,2); #应该加工数量
    declare shuliang_order numeric(8,2); #订单数量
    declare shuliang_product numeric(8,2); #实际加工数量
    declare orignid varchar(20); #成品对应的原纸物料编码
    declare ordermasterid_ varchar(20); #销售单号
    declare outid varchar(20); #出仓单号
    declare pieceid varchar(20); #件号
    declare sheet_num int; #张数
    declare price_ numeric(8,2); #单价
    declare price_single numeric(8,2); #金额
    declare outdate date; #出仓日期
    declare customer_ varchar(20); #客户名
    declare orderdate date; #客户下单时间
    declare outdate_ date; #出仓日期
    declare total_ numeric(8,2); #合计
    declare brand_ varchar(20); #品牌
    declare papertype_ varchar(20); #纸种
    declare rank_ char(2); #级别
    declare gweight_ numeric(8,2); #克重
    declare specification_ varchar(10); #规格
    declare unit_ char(2); #单位
    declare price_cut_ numeric(8,2); #分切费
    declare shuliang_all numeric(8,2); #出仓数量
    declare sheet_all int; #出仓张数
    declare flag_first int default false; #第一个游标的终止标记，用于判断表是否遍历完毕
    declare cur_a cursor for select itemid,ordermasterid,quantity,price from saleconfirm where state = 1 and state_ok = 0 and producttype='成品';
    declare cur_b cursor for select ordermasterid,buy,saledate from saleconfirmmaster where state = 1 and state_ok = 0 and price_cut >0;

    declare continue handler for not found set flag_first = true;
    open cur_a;
    loop_a: loop
        fetch next from cur_a into itemid_,ordermasterid_,shuliang_cut,price_; #将游标每行值赋给变量
        if flag_first then #如果遍历完毕，退出循环
            leave loop_a;
        end if;
        set shuliang_order = (select quantity from orderdetail where itemid = itemid_ and ordermasterid = ordermasterid_); #订单数量
        set pieceid = ((select count(*) from finishoutstorage)+1); #生成件号materielmaterielmateriel
        set sheet_num = Floor(3325*shuliang_order); #张数换算,数量*3325
        set brand_ = (select brand from materiel where itemid = itemid_); #品牌
        set papertype_ = (select papertype from materiel where itemid = itemid_); #纸种
        set gweight_ = (select gweight from materiel where itemid = itemid_); #克重
        set rank_ = (select rank from materiel where itemid = itemid_); #级别
        set specification_ = (select specification from materiel where itemid = itemid_); #规格
        set unit_ = (select unit from materiel where itemid = itemid_); #单位
        set price_single = shuliang_order*price_; #金额
        set orignid = (select orign from materiel where itemid = itemid_); #成品对应的原纸
        set shuliang_product = shuliang_cut * 1.1*(1-rand()*0.05); #实际生产的数量
        update repetory set quantity = (quantity - shuliang_cut * 1.1) where itemid = orignid; #分切过程，减去库存表中对应的原纸
        update repetory set quantity = (quantity + shuliang_product - shuliang_order) where itemid = itemid_; #分切过程，增加成品,（有0.05以下的损耗率）
        update saleconfirm set state_ok = 1 where ordermasterid = ordermasterid_ and itemid = itemid_; #修改销售确认书明细表对应字段值
        insert into finishoutstorage values(ordermasterid_,pieceid,brand_,papertype_,rank_,gweight_,specification_,unit_,shuliang_order,sheet_num,price_,price_single); #生成分切成品出仓明细表
        insert into cut_outstorage values(ordermasterid_,brand_,papertype_,rank_,gweight_,specification_,unit_,shuliang_product,sheet_num,price_,price_single); #生成分切出仓明细表
    end loop;
    close cur_a;
    set flag_first = false;
    open cur_b;
    loop_b: loop
        fetch next from cur_b into ordermasterid_,customer_,orderdate;
        if flag_first then #如果遍历完毕，退出循环
            leave loop_b;
        end if;
        set outdate_ = date_add(orderdate,interval 2 day); #设置出仓日期，设加工需要2天
        set outid = (select count(*) from finishoutstoragemaster)+1; #成品出仓单明细表单号
        set total_ = (select sum(price_single) from finishoutstorage where ordermasterid = ordermasterid_); #统计总价格
        set price_cut_ = (select price_cut from saleconfirmmaster where ordermasterid = ordermasterid_); #分切费
        set shuliang_all = (select sum(quantity) from finishoutstorage where ordermasterid = ordermasterid_); #统计数量
        set sheet_all = (select sum(sheet_num) from finishoutstorage where ordermasterid = ordermasterid_); #统计数量
        update saleconfirmmaster set state_ok = 1 where ordermasterid = ordermasterid_; #修改销售确认书总表的完成状态
        insert into finishoutstoragemaster values(outid,ordermasterid_,customer_,outdate_,orderdate,total_,sheet_all,shuliang_all,price_cut_); #分切出仓单
        insert into cut_outstoragemaster values(outid,ordermasterid_,customer_,outdate_,orderdate,total_,sheet_all,shuliang_all); #出仓成品单
    end loop;
    close cur_b;
end;

drop procedure if exists cal_sale;
create procedure cal_sale()
begin 
    declare shuliang numeric(8,2); #从订单表获取数量
    declare sale_ varchar(20) default  '隆 楷 纸 业';
    declare price_ numeric(8,2);  #由特价表和价格表生成的最终价格
    declare customer_ varchar(20); #买家
    declare price_cut numeric(8,2); #分切费
    declare total_ numeric(8,2); #总价格
    declare brand_ varchar(20); #品牌
    declare ordermasterid_ varchar(20); #订单编号
    declare papertype_ varchar(20); #纸种
    declare rank_ char(2); #级别
    declare gweight_ numeric(8,2); #克重,
    declare specification_ varchar(10); #规格
    declare unit_ char(2); #单位
    declare producttype_ varchar(10); #产品类型
    declare itemid_ varchar(20); #物料编码
    declare orderdate date; #订单时间
    declare suredate date; #确认时间
    declare count_confirm int; #销售确认书已有的行数
    declare flag_first int default false; #第一个游标的终止标记，用于判断表是否遍历完毕
    declare cur_a cursor for select itemid,ordermasterid,brand,itemid,papertype,rank,gweight,specification,unit,producttype,quantity from orderdetail where producttype = '成品' and state = 0; 
    declare cur_b cursor for select ordermasterid,ordertime,suretime,customer from ordermaster where state = 0;
    declare continue handler for not found set flag_first = true;
    open cur_a;
    loop_a: loop
        fetch next from cur_a into itemid_,ordermasterid_,brand_,itemid_,papertype_,rank_,gweight_,specification_,unit_,producttype_,shuliang;
        if flag_first then #如果遍历完毕，退出循环
            leave loop_a;
        end if;
        set shuliang = shuliang - (select quantity from repetory where itemid = itemid_); #将订单中要求的成品数减去库存中的成品数
        update orderdetail set state = 1 where ordermasterid = ordermasterid_ and itemid = itemid_; #已处理订单
        if shuliang>0 then
            #set count_confirm = (select count(*) from saleconfirm)+1;
            set price_ = (select price from sale_price where itemid = itemid_);
            if shuliang > 1 then #如果购买数量超过两吨，按特价表设置价格
                set price_ = (select price from specialprice where itemid = itemid_);
            end if;
            #set price_cut = 500*shuliang; #分切费，每吨500元
            #set total_ = price_cut + price_*shuliang; #设置总价格
            insert into saleconfirm values(itemid_,ordermasterid_,brand_,papertype_,rank_,gweight_,specification_,unit_,producttype_,shuliang,price_,0,0);
        end if;
    end loop;
    close cur_a;
    set flag_first = false; #重置终止条件
    open cur_b;
    loop_b: loop
        fetch next from cur_b into ordermasterid_,orderdate,suredate,customer_;
        if flag_first then #如果遍历完毕，退出循环
            leave loop_b;
        end if;
        set producttype_ = '成品';
        if producttype_ in (select producttype from orderdetail where ordermasterid = ordermasterid_) then
            update ordermaster set state = 1 where ordermasterid = ordermasterid_; #修改订单主表中的状态
            set price_cut = (select sum(quantity) from saleconfirm)*500; #计算分切费，每吨500元
            set total_ = (select sum(price*quantity) from saleconfirm)+price_cut; #计算总费用
            insert into saleconfirmmaster values(ordermasterid_,orderdate,suredate,customer_,sale_,price_cut,total_,0,0);
        end if;
    end loop;
    close cur_b;
end;



drop procedure if exists cal_no_cut_sale;
create procedure cal_no_cut_sale()
begin 
    declare shuliang numeric(8,2); #从订单表获取数量
    declare sale_ varchar(20) default '隆 楷 纸 业';
    declare price_ numeric(8,2);  #由特价表和价格表生成的最终价格
    declare customer_ varchar(20); #买家
    declare price_cut numeric(8,2) default 0; #分切费
    declare total_ numeric(8,2); #总价格
    declare brand_ varchar(20); #品牌
    declare ordermasterid_ varchar(20); #订单编号
    declare papertype_ varchar(20); #纸种
    declare rank_ char(2); #级别
    declare gweight_ numeric(8,2); #克重,
    declare specification_ varchar(10); #规格
    declare unit_ char(2); #单位
    declare producttype_ varchar(10); #产品类型
    declare itemid_ varchar(20); #物料编码
    declare orderdate date; #订单时间
    declare suredate date; #确认时间
    declare count_confirm int; #销售确认书已有的行数
    declare flag_first int default false; #第一个游标的终止标记，用于判断表是否遍历完毕

    declare cur_a cursor for select ordermasterid,brand,itemid,papertype,rank,gweight,specification,unit,producttype,quantity from orderdetail where producttype='原纸' and state = 0; 
    declare cur_b cursor for select ordermasterid,ordertime,suretime,customer from ordermaster where state = 0;
    declare continue handler for not found set flag_first = true;
    open cur_a;
    loop_a: loop
        fetch next from cur_a into ordermasterid_,brand_,itemid_,papertype_,rank_,gweight_,specification_,unit_,producttype_,shuliang;
        if flag_first then #如果遍历完毕，退出循环
            leave loop_a;
        end if;

            set price_ = (select price from sale_price where itemid = itemid_);
            update orderdetail set state = 1 where ordermasterid = ordermasterid_ and itemid = itemid_; #已处理订单
            insert into saleconfirm values(itemid_,ordermasterid_,brand_,papertype_,rank_,gweight_,specification_,unit_,producttype_,shuliang,price_,0,0);
    end loop;
    close cur_a;

    set flag_first = false; #重置终止条件
    open cur_b;
    loop_b: loop
        fetch next from cur_b into ordermasterid_,orderdate,suredate,customer_;
        if flag_first then #如果遍历完毕，退出循环
            leave loop_b;
        end if;
        set total_ = (select sum(price*quantity) from saleconfirm); #计算总费用
        set producttype_ = '原纸';
        if producttype_ in (select producttype from orderdetail where ordermasterid = ordermasterid_) then
            update ordermaster set state = 1 where ordermasterid = ordermasterid_; #修改订单主表中的状态
            insert into saleconfirmmaster values(ordermasterid_,orderdate,suredate,customer_,sale_,price_cut,total_,0,0);
        end if;
    end loop;
    close cur_b;
end;



drop procedure if exists no_cut_product;
create procedure no_cut_product()
begin
    declare itemid_ varchar(20); #物料编码
    declare n int; #出仓主表的行数
    declare shuliang_cut numeric(8,2); #应该加工数量
    declare shuliang_order numeric(8,2); #订单数量
    declare shuliang_product numeric(8,2); #实际加工数量
    declare orignid varchar(20); #成品对应的原纸物料编码
    declare ordermasterid_ varchar(20); #销售单号
    declare outid varchar(20); #出仓单号
    declare pieceid varchar(20); #件号
    declare sheet_num int; #张数
    declare price_ numeric(8,2); #单价
    declare price_single numeric(8,2); #金额
    declare outdate date; #出仓日期
    declare customer_ varchar(20); #客户名
    declare orderdate date; #客户下单时间
    declare outdate_ date; #出仓日期
    declare total_ numeric(8,2); #合计
    declare brand_ varchar(20); #品牌
    declare papertype_ varchar(20); #纸种
    declare rank_ char(2); #级别
    declare gweight_ numeric(8,2); #克重
    declare specification_ varchar(10); #规格
    declare unit_ char(2); #单位
    declare price_cut_ numeric(8,2); #分切费
    declare shuliang_all numeric(8,2); #出仓数量
    declare sheet_all int; #出仓张数
    declare flag_first int default false; #第一个游标的终止标记，用于判断表是否遍历完毕
    declare cur_a cursor for select itemid,ordermasterid,quantity,price from saleconfirm where state = 1 and state_ok = 0 and producttype='原纸';
    declare cur_b cursor for select ordermasterid,buy,saledate from saleconfirmmaster where state = 1 and state_ok = 0 and price_cut = 0;
    declare continue handler for not found set flag_first = true;
    open cur_a;
    loop_a: loop
        fetch next from cur_a into itemid_,ordermasterid_,shuliang_cut,price_; #将游标每行值赋给变量
        if flag_first then #如果遍历完毕，退出循环
            leave loop_a;
        end if;
	      set brand_ = (select brand from materiel where itemid = itemid_); #品牌
        set papertype_ = (select papertype from materiel where itemid = itemid_); #纸种
        set gweight_ = (select gweight from materiel where itemid = itemid_); #克重
        set rank_ = (select rank from materiel where itemid = itemid_); #级别
        set specification_ = (select specification from materiel where itemid = itemid_); #规格
        set unit_ = (select unit from materiel where itemid = itemid_); #单位
        set price_single = shuliang_cut*price_; #金额
        set shuliang_order = (select quantity from orderdetail where itemid = itemid_ and ordermasterid = ordermasterid_); #获取订单数量
        set shuliang_product = shuliang_cut * 1.1*(1-rand()*0.05); #实际取出的原纸
        update saleconfirm set state_ok = 1 where ordermasterid = ordermasterid_ and itemid = itemid_; #修改销售确认书明细表对应字段值
        update repetory set quantity = (quantity - shuliang_cut * 1.1) where itemid = itemid_; #减去库存表的原纸
        update repetory set quantity = (quantity + shuliang_product - shuliang_order) where itemid = itemid_; #分切过程，增加成品,（有0.05以下的损耗率）
        insert into ncut_outstorage values(ordermasterid_,brand_,papertype_,rank_,gweight_,specification_,unit_,shuliang_order,price_,price_single); #生成非分切出仓明细表
    end loop;
    close cur_a;

    set flag_first = false;
    open cur_b;
    loop_b: loop
        fetch next from cur_b into ordermasterid_,customer_,orderdate;
        if flag_first then #如果遍历完毕，退出循环
            leave loop_b;
        end if;
        set outdate_ = date_add(orderdate,interval 2 day); #设置出仓日期，设加工需要2天
        set outid = (select count(*) from nfinishoutstorage)+1; #非分切出仓单总表号
        set total_ = (select sum(price_single) from ncut_outstorage where ordermasterid = ordermasterid_); #统计总价格
        update saleconfirmmaster set state_ok = 1 where ordermasterid = ordermasterid_; #修改销售确认书总表的完成状态
        insert into nfinishoutstorage values(outid,ordermasterid_,customer_,outdate_,orderdate,total_); #非分切出仓单总表
    end loop;
    close cur_b;
end;

select * from ordermaster where ordermasterid='A0001';

select * from ordermaster where ordermasterid='A0001';




