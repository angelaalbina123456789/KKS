1. настройка интерфейсов
Interface GigabitEthernet0/1.800
encapsulation dot1Q 800
ip adress 192.168.1.126 255.255.255.128

2. настройка DHCP
ip dhcp pool subnet1
network 192.168.1.0 255.255.255.128
default-router 192.168.1.126

3. настройка интерфейса loopback 0 для идентификации ospf (по одному lookback на каждый роутер по)
ip adrees 192.168.101.2 255.255.255.255

4. настройка процесса ospf
router ospf 1
network 192.168.2.0 0.0.0.3 area 0
network 192.168.2.0 0.0.0.127 area 0
network 192.168.2.0 0.0.0.15 area 0

5.настройка маршрута по умолчанию (на граничном роутере)
ip route 0.0.0.0 0.0.0.0 211.211.211.2

6.включение режима обмена дефолтным маршрутом (на граничном роутере)
router ospf 1
default-information originate

7. объявление внутренних и внешних интерфейсов для сетевой трансляции (на граничном роутере)
interface GigabitEthernet0/0
	ip nat outside
interface GigabitEthernet0/0/0
	ip nat inside
interface gigabitEthernet0/1/0
	ip nat inside

8. создание access-list для NAT (на граничном роутере)
ip access-list standard NAT
permit 192.168.1.0 0.0.0.127
permit 192.168.1.160 0.0.0.15
permit 192.168.1.128 0.0.0.31

9. команда для включения dynamit NAT (overload) (на граничном роутере)
ip nat inside source list NAT interface GigabitEthernet0/0 overload
