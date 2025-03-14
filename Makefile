.PHONY: up down restart logs build clean

up:
	docker-compose up -d

down:
	docker-compose down

restart:
	docker-compose down && docker-compose up -d

logs:
	docker-compose logs -f

build:
	docker-compose build

clean:
	docker-compose down --volumes --remove-orphans

postgresql-up:
	docker-compose up -d postgres

redis-up:
	docker-compose up -d redis