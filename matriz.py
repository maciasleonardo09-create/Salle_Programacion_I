# MULTIPLICACION DE MATRICES

filaA = int(input("Ingresa el numero de filas de A: "))
colA = int(input("Ingresa el numero de columnas de A: "))

filaB = int(input("Ingresa el numero de filas de B: "))
colB = int(input("Ingresa el numero de columnas de B: "))

if colA != filaB:
    print("Error, Las columnas de A deben coincidir con las filas de B.")
else:
    A = [[0 for _ in range(colA)] for _ in range(filaA)]
    B = [[0 for _ in range(colB)] for _ in range(filaB)]
    C = [[0 for _ in range(colB)] for _ in range(filaA)]

    print("")
    print("Ingresa los valores de la matriz A")
    for i in range(filaA):
        for j in range(colA):
            A[i][j] = int(input(f"A[{i+1},{j+1}]: "))

    print("")
    print("Ingresa los valores de la matriz B")
    for i in range(filaB):
        for j in range(colB):
            B[i][j] = int(input(f"B[{i+1},{j+1}]: "))

    for i in range(filaA):
        for j in range(colB):
            C[i][j] = 0
            for k in range(colA):
                C[i][j] += A[i][k] * B[k][j]

    print("")
    print("Matriz A")
    for i in range(filaA):
        for j in range(colA):
            print(A[i][j], end=" ")
        print("")

    print("")
    print("Matriz B")
    for i in range(filaB):
        for j in range(colB):
            print(B[i][j], end=" ")
        print("")

    print("")
    print("Matriz resultado C = A x B")
    for i in range(filaA):
        for j in range(colB):
            print(C[i][j], end=" ")
        print("")