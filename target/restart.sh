#!/bin/sh

while true
do
    sh start.sh
    echo "Для остановки сервера нужно нажать Ctrl + C!"
    echo "Рестарт скрипта:"
    for i in 1
    do
        echo "$i..."
        sleep 1
    done
    echo "Рестарт завершен!"
done