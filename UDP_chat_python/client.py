import socket
import threading

username = input("Choose a username: ")

client = socket.socket(socket.AF_NET, socket.SOCK_STREAM)
client.connect(('10.0.0.175', 22222))



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
