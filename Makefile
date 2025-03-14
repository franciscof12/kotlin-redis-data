.PHONY: up down restart logs build clean

up:
	cp .env.docker .env
	docker-compose up -d

down:
	docker-compose down

restart:
	docker-compose down && docker-compose up -d

logs:
	docker-compose logs -f

clean:
	docker-compose down --volumes --remove-orphans

postgresql-up:
	docker-compose up -d postgres

redis-up:
	docker-compose up -d redis