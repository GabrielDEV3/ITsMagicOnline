# ITsMagicOnline


# Documentation

# class Database {

Classe que gerencia a interação com um banco de dados remoto (JSON).

## Atributos
- `reference`: String que contém a URL base para a referência do banco de dados.
- `child`: String que representa o caminho do filho no banco de dados.
- `key`: Chave do objeto no banco de dados.
- `extension`: Extensão do arquivo utilizado no banco de dados (".json").

## Construtores
- `Database(String url)`: Constrói um objeto Database com uma URL base.
- `Database(String url, String child, String key)`: Constrói um objeto Database com URL base, caminho de filho e chave.

## Métodos
- `child(String other)`: Cria uma nova instância de `Database` com um caminho filho adicional.
- `getMain()`: Retorna uma nova instância de `Database` com a URL base.
- `setTalker(DatabaseTalker talker)`: Inicia um processo em segundo plano para interagir com o banco de dados e enviar dados.
- `setListener(DatabaseListener listener)`: Inicia um processo em segundo plano para escutar as alterações no banco de dados.
- `setTalkerForSingleValueEvent(DatabaseTalker talker)`: Inicia uma interação com o banco de dados para um único evento.
- `setListenerForSingleValueEvent(DatabaseListener listener)`: Escuta um único evento do banco de dados.
- `readStream(InputStream inputStream)`: Lê o conteúdo de um stream de entrada.
- `writeStream(OutputStream outputStream, String message)`: Escreve uma mensagem em um stream de saída.

---

# class DataSnapshot {

Classe que representa uma captura de dados de um banco de dados.

## Atributos
- `key`: Chave do valor no banco de dados.
- `value`: Valor do dado armazenado.

## Construtores
- `DataSnapshot(String key, Object value)`: Constrói uma instância de `DataSnapshot` com uma chave e um valor.

## Métodos
- `getValue(Class<T> clazz)`: Retorna o valor do tipo desejado.
- `getValue()`: Retorna o valor armazenado como um objeto genérico.
- `getKey()`: Retorna a chave do `DataSnapshot`.
- `child(String other)`: Retorna um `DataSnapshot` do filho especificado.
- `hasChild(String key)`: Verifica se existe um filho com a chave fornecida.
- `hasChildren()`: Verifica se existem filhos.
- `getChildren()`: Retorna uma lista de `DataSnapshot` para todos os filhos.
- `iterate()`: Retorna um iterador para os filhos.
- `getChildrenCount()`: Retorna o número de filhos.
- `exists()`: Verifica se o `DataSnapshot` contém um valor.
- `toString()`: Retorna o valor como uma string.

---

# interface DatabaseTalker {

Interface para um objeto que envia dados para o banco de dados.

## Métodos
- `isRunning()`: Retorna `true` se o processo estiver em execução.
- `talk()`: Retorna uma mensagem para ser enviada ao banco de dados.

---

# interface DatabaseListener {

Interface para um objeto que escuta alterações no banco de dados.

## Métodos
- `isRunning()`: Retorna `true` se o ouvinte estiver em execução.
- `listen(DataSnapshot value)`: Escuta as alterações e lida com os dados recebidos.

---

# class AtomicString {

Classe que encapsula uma string com suporte a operações atômicas.

## Atributos
- `atomicString`: Referência atômica de uma string.

## Construtores
- `AtomicString(String initialValue)`: Constrói uma instância de `AtomicString` com um valor inicial.

## Métodos
- `get()`: Retorna o valor armazenado.
- `set(String newValue)`: Define um novo valor para a string.
- `append(String additionalValue)`: Adiciona um valor ao final da string.
- `compareAndSet(String expectedValue, String newValue)`: Compara e define a string se o valor esperado for igual ao valor atual.

---

# class Deserializer {

Classe que faz a desserialização de objetos a partir de JSON para tipos específicos.

## Métodos
- `toVector2(String json)`: Converte um JSON para um objeto `Vector2`.
- `toVector3(String json)`: Converte um JSON para um objeto `Vector3`.
- `toVector4(String json)`: Converte um JSON para um objeto `Vector4`.

---

# class Serializer {

Classe que faz a serialização de objetos para JSON.

## Métodos
- `fromVector2(Vector2 vector2)`: Converte um objeto `Vector2` para um JSON.
- `fromVector3(Vector3 vector3)`: Converte um objeto `Vector3` para um JSON.
- `fromVector4(Vector4 vector4)`: Converte um objeto `Vector4` para um JSON.
