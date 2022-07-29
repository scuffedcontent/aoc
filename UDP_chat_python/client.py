from multiprocessing import process
import socket
import threading

username = input("Choose a username: ")

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)


host = process.env.myHost
port = process.env.myPort

client.connect((port, host))



def receive():
    while True:
        try:
            message = client.recv(1024).decode('ascii')
            if message == 'USER':
                client.send(username.encode('ascii'))
            else:
                print(message)
        except:
            print("An error occurred!")
            client.close()
            break

def write():
    while True:
        message = f'{username}: {input("")}'
        client.send(message.encode('ascii'))

receive_thread = threading.Thread(target=receive)
receive_thread.start()

write_thread = threading.Thread(target=write)
write_thread.start()
