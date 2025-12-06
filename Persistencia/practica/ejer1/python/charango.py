import json

class Charango:
    def __init__(self, material, nro_cuerdas, cuerdas):
        self.material = material
        self.nro_cuerdas = nro_cuerdas
        self.cuerdas = cuerdas  # lista de booleanos

    def contar_cuerdas_false(self):
        return sum(1 for c in self.cuerdas if not c)

    def __str__(self):
        return f"Charango [{self.material}, {self.nro_cuerdas}, {self.cuerdas}]"

    
    def to_dict(self):
        return {
            'material': self.material,
            'nro_cuerdas': self.nro_cuerdas,
            'cuerdas': self.cuerdas
        }

    @staticmethod
    def from_dict(d):
        return Charango(d['material'], d['nro_cuerdas'], d['cuerdas'])


class OperacionesCharango:

    @staticmethod
    def grabar(lista, archivo="charango.json"):
        with open(archivo, 'w') as f:
            json.dump([c.to_dict() for c in lista], f, indent=4)
        print("Objetos grabados en JSON...")

    @staticmethod
    def leer(archivo="charango.json"):
        try:
            with open(archivo, 'r') as f:
                datos = json.load(f)
            return [Charango.from_dict(d) for d in datos]
        except FileNotFoundError:
            print("Archivo no encontrado.")
            return []

    @staticmethod
    def eliminar_mal_estado(lista):
        i = 0
        while i < len(lista):
            if lista[i].contar_cuerdas_false() > 6:
                lista.pop(i)
            else:
                i += 1

    @staticmethod
    def listar_por_material(lista, material):
        print(f"\nCharangos de material: {material}")
        for c in lista:
            if c.material.lower() == material.lower():
                print(c)

    @staticmethod
    def buscar_10_cuerdas(lista):
        print("\nCharangos con 10 cuerdas:")
        for c in lista:
            if c.nro_cuerdas == 10:
                print(c)

    @staticmethod
    def ordenar_por_material(lista):
        n = len(lista)
        for i in range(n-1):
            for j in range(i+1, n):
                if lista[i].material.lower() > lista[j].material.lower():
                    lista[i], lista[j] = lista[j], lista[i]


if __name__ == "__main__":

    lista = [
        Charango("Madera", 10, [True, True, True, False, True, False, True, True, False, False]),
        Charango("Plastico", 8, [True]*10),
        Charango("Carbono", 10, [False]*10),
        Charango("Cuero", 10, [True, True, True, True, True, True, True, False, True, True]),
        Charango("Roble", 10, [False, False, False, False, False, False, False, True, True, True]),
        Charango("Madera", 8, [True]*10),
        Charango("Aluminio", 10, [True, False, True, True, True, True, True, True, True, True])
    ]

    OperacionesCharango.grabar(lista)
    datos = OperacionesCharango.leer()

    print("\n===== DATOS LEÍDOS DEL ARCHIVO =====")
    for c in datos:
        print(c)

    OperacionesCharango.eliminar_mal_estado(datos)

    print("\n===== DESPUÉS DE ELIMINAR =====")
    for c in datos:
        print(c)

    OperacionesCharango.listar_por_material(datos, "Madera")
    OperacionesCharango.buscar_10_cuerdas(datos)
    OperacionesCharango.ordenar_por_material(datos)

    print("\n===== ORDENADOS POR MATERIAL (A-Z) =====")
    for c in datos:
        print(c)
