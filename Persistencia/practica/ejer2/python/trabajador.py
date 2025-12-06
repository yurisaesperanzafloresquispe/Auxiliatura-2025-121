import json
import os

class Trabajador:
    def __init__(self, nombre, carnet, salario):
        self.nombre = nombre
        self.carnet = carnet
        self.salario = salario

    def set_salario(self, salario):
        self.salario = salario

    def __str__(self):
        return f"{self.nombre} - {self.carnet} - {self.salario}"

    def to_dict(self):
        return {
            'nombre': self.nombre,
            'carnet': self.carnet,
            'salario': self.salario
        }

    @staticmethod
    def from_dict(d):
        return Trabajador(d['nombre'], d['carnet'], d['salario'])

    def __eq__(self, other):
        if isinstance(other, Trabajador):
            return self.carnet == other.carnet
        return False

    def __hash__(self):
        return hash(self.carnet)

class ArchivoTrabajador:
    def __init__(self, nombre_arch):
        self.nombre_arch = nombre_arch
        self.lista = []

    def crear_archivo(self):
        if not os.path.exists(self.nombre_arch):
            with open(self.nombre_arch, 'w') as f:
                json.dump([], f)
            print(f"Archivo creado: {self.nombre_arch}")
        else:
            print("El archivo ya existe.")

    def leer_desde_archivo(self):
        if os.path.exists(self.nombre_arch):
            with open(self.nombre_arch, 'r') as f:
                datos = json.load(f)
                self.lista = [Trabajador.from_dict(d) for d in datos]
            print("Lista de trabajadores cargada desde archivo.")
        else:
            print("Archivo no encontrado, se creará uno nuevo.")
            self.crear_archivo()

    def guardar_trabajador(self, t):
        if t not in self.lista:
            self.lista.append(t)
            print(f"Trabajador guardado en memoria: {t.nombre}")
        else:
            print(f"Trabajador {t.nombre} ya existe en la lista.")

    def guardar_en_archivo(self):
        with open(self.nombre_arch, 'w') as f:
            json.dump([t.to_dict() for t in self.lista], f, indent=4)
        print("Lista de trabajadores guardada en archivo.")

    def aumenta_salario(self, aumento, t):
        t.set_salario(t.salario + aumento)
        print(f"Nuevo salario de {t.nombre}: {t.salario}")

    def buscar_mayor_salario(self):
        if not self.lista:
            return None
        return max(self.lista, key=lambda t: t.salario)

    def ordenar_por_salario(self):
        self.lista.sort(key=lambda t: t.salario, reverse=True)
        print("Trabajadores ordenados por salario (mayor a menor).")

    def mostrar(self):
        for t in self.lista:
            print(t)

    def existe_trabajador(self, t):
        return t in self.lista

if __name__ == "__main__":
    arch = ArchivoTrabajador("trabajadores.json")


    arch.leer_desde_archivo()


    arch.crear_archivo()
    trabajadores = [
        Trabajador("Valeria", 12345, 3500),
        Trabajador("Andres", 56789, 4000),
        Trabajador("Isabella", 99999, 2500),
        Trabajador("Carlos", 11111, 3700),
        Trabajador("Elena", 22222, 3200),
        Trabajador("Sofia", 33333, 4100),
        Trabajador("Diego", 44444, 2900)
    ]

    for t in trabajadores:
        if not arch.existe_trabajador(t):
            arch.guardar_trabajador(t)

    arch.aumenta_salario(500, trabajadores[2])  # Isabella
    arch.aumenta_salario(300, trabajadores[4])  # Elena
    arch.aumenta_salario(700, trabajadores[5])  # Sofia


    arch.guardar_en_archivo()


    print("\nTrabajador con mayor salario:")
    print(arch.buscar_mayor_salario())


    arch.ordenar_por_salario()
    print("\nTrabajadores ordenados por salario:")
    arch.mostrar()
