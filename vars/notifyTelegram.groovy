def call(String message) {
    def botToken = '8001171198:AAGL-7k3jx3ZK1FrnaXjct5O-wMNXW4Dkj4'
    def chatId   = '-4667714217'

    def url = "https://api.telegram.org/bot${botToken}/sendMessage"

    def payload = """
        curl -s -X POST ${url} \
        -d chat_id=${chatId} \
        -d text="${message}" \
        -d parse_mode=Markdown
    """

    sh payload
}

